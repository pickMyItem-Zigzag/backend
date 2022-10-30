package kakaostyle.pickMyItem.itempick.service

import kakaostyle.pickMyItem.itempick.domain.Board
import kakaostyle.pickMyItem.itempick.domain.Pick
import kakaostyle.pickMyItem.itempick.domain.Post
import kakaostyle.pickMyItem.itempick.domain.User
import kakaostyle.pickMyItem.itempick.dto.CreatePostInput
import kakaostyle.pickMyItem.itempick.dto.DeletePostInput
import kakaostyle.pickMyItem.itempick.dto.PostResponse
import kakaostyle.pickMyItem.itempick.repository.BoardJpaRepository
import kakaostyle.pickMyItem.itempick.repository.PostJpaRepository
import kakaostyle.pickMyItem.itempick.repository.UserJpaRepository
import kakaostyle.pickMyItem.utils.isNullOrFalse
import kakaostyle.pickMyItem.wishitem.dto.ItemInfoInput
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostService(
    private val boardJpaRepository: BoardJpaRepository,
    private val postJpaRepository: PostJpaRepository,
    private val userJpaRepository: UserJpaRepository,
) {
    val logger: Logger = LoggerFactory.getLogger(this::class.simpleName)

    @Transactional(readOnly = true)
    fun getPost(postId: Long): PostResponse {
        return PostResponse.from(
            postJpaRepository.findPostById(postId).takeIf { it?.deleted.isNullOrFalse() }
                ?: throw RuntimeException("해당하는 게시글이 없습니다.")
        )
    }

    @Transactional(readOnly = true)
    fun getAllPostList(): List<PostResponse> {
        return postJpaRepository.findAll()
            .filter { it.deleted.isNullOrFalse() }
            .map { PostResponse.from(it) }
            .toList()
    }

    @Transactional(readOnly = true)
    fun getTotalPickCount(postId: Long): Int {
        return postJpaRepository
            .findPostById(postId)
            .takeIf { it?.deleted.isNullOrFalse() }
            ?.getTotalPickCount()
            ?: throw RuntimeException("해당하는 게시글이 없습니다.")
    }

    @Transactional
    fun createPost(input: CreatePostInput) {
        val board = boardJpaRepository.findBoardById(input.boardId).takeIf { it?.deleted.isNullOrFalse() }
            ?: throw RuntimeException("해당하는 게시글이 없습니다.")
        val user = userJpaRepository.findUserById(input.userId).takeIf { it?.deleted.isNullOrFalse() }
            ?: throw RuntimeException("해당하는 유저가 없습니다.")
        val post = savePost(input.postTitle, input.content ?: "", board, user)
        addPickItemToPost(input.itemInfoInputList, post)
        logger.info("POST 생성완료: $post.id, ${post.title}, ${post.content}")
    }

    private fun addPickItemToPost(itemList: List<ItemInfoInput>, post: Post) {
        itemList.forEach {
            post.addPick(
                Pick.from(it.itemId, it.itemName, it.itemImageUrl)
            )
        }
    }

    private fun savePost(
        postTitle: String,
        content: String,
        board: Board,
        user: User,
    ): Post {
        val post = Post(
            title = postTitle,
            content = content,
            pickList = mutableListOf(),
            board = board,
            postingUser = user,
        )
        user.addMyPostingList(post)
        return board.addPost(post)
    }

    @Transactional(readOnly = true)
    fun getPickResult(postId: Long): List<PickResult> {
        val post = postJpaRepository.findPostById(postId).takeIf { it?.deleted.isNullOrFalse() }
            ?: throw RuntimeException("해당하는 게시글이 없습니다.")
        val totalPickCount = post.getTotalPickCount()

        return post.pickList.map {
            PickResult(
                pickId = it.id,
                itemId = it.itemId,
                pickResult = it.pickCount.toDouble() / totalPickCount
            )
        }
    }

    @Transactional
    fun deletePost(deletePostInput: DeletePostInput) {
        val user = userJpaRepository.findUserById(deletePostInput.userId).takeIf { it?.deleted.isNullOrFalse() }
            ?: throw RuntimeException("해당하는 유저가 없습니다.")
        val post = postJpaRepository.findPostById(deletePostInput.postId).takeIf { it?.deleted.isNullOrFalse() }
            ?: throw RuntimeException("해당하는 게시글이 없습니다.")
        user.deleteMyPosting(post)
    }
}

data class PickResult(
    val pickId: Long,
    val itemId: Long,
    val pickResult: Double,
)
package kakaostyle.pickMyItem.itempick.service

import kakaostyle.pickMyItem.itempick.domain.Board
import kakaostyle.pickMyItem.itempick.repository.BoardJpaRepository
import kakaostyle.pickMyItem.itempick.domain.Pick
import kakaostyle.pickMyItem.itempick.domain.Post
import kakaostyle.pickMyItem.itempick.dto.CreatePostInput
import kakaostyle.pickMyItem.itempick.dto.PostResponse
import kakaostyle.pickMyItem.itempick.repository.PostJpaRepository
import kakaostyle.pickMyItem.wishitem.dto.ItemInfoInput
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostService(
    private val boardJpaRepository: BoardJpaRepository,
    private val postJpaRepository: PostJpaRepository,
) {
    val logger: Logger = LoggerFactory.getLogger(this::class.simpleName)

    @Transactional(readOnly = true)
    fun getPost(id: Long): PostResponse {
        return PostResponse.from(postJpaRepository.findById(id).orElseThrow { RuntimeException("해당하는 게시글이 없습니다.") })
    }

    @Transactional(readOnly = true)
    fun getAllPostList(): List<PostResponse> {
        return postJpaRepository.findAll()
            .filter { it.deleted == null }
            .map { PostResponse.from(it) }
            .toList()
    }

    @Transactional(readOnly = true)
    fun getTotalPickCount(postId: Long): Int {
        return postJpaRepository.findById(postId)
            .orElseThrow { RuntimeException("해당하는 게시글이 없습니다.") }
            .getTotalPickCount()
    }

    @Transactional
    fun createPost(input: CreatePostInput) {
        val board = boardJpaRepository.findBoardById(input.boardId) ?: throw RuntimeException("해당하는 게시글이 없습니다.")
        val post = savePost(input.postTitle, input.content ?: "", board)
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
        board: Board
    ): Post {
        val post = Post(
            title = postTitle,
            content = content,
            pickList = mutableListOf(),
            board = board
        )
        return board.addPost(post)
    }

    @Transactional(readOnly = true)
    fun getPickResult(postId: Long): List<PickResult> {
        val post = postJpaRepository.findById(postId)
            .orElseThrow { RuntimeException("해당하는 게시글이 없습니다.") }
        val totalPickCount = post.getTotalPickCount()

        return post.pickList.map {
            PickResult(
                pickId = it.id,
                itemId = it.itemId,
                pickResult = it.pickCount.toDouble() / totalPickCount
            )
        }
    }
}

data class PickResult(
    val pickId: Long,
    val itemId: Long,
    val pickResult: Double,
)
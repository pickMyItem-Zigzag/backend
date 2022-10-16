package kakaostyle.pickMyItem.post.service

import kakaostyle.pickMyItem.board.domain.Board
import kakaostyle.pickMyItem.board.repository.BoardJpaRepository
import kakaostyle.pickMyItem.pick.domain.Pick
import kakaostyle.pickMyItem.post.domain.Post
import kakaostyle.pickMyItem.post.dto.CreatePostInput
import kakaostyle.pickMyItem.post.dto.PostResponse
import kakaostyle.pickMyItem.post.repository.PostJpaRepository
import kakaostyle.pickMyItem.utils.ItemInfoInput
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
        itemList.forEach { post.addPick(Pick.from(it)) }
    }

    private fun savePost(
        postTitle: String,
        content: String,
        board: Board
    ): Post {
        return postJpaRepository.save(
            Post(
                title = postTitle,
                content = content,
                pickList = mutableListOf(),
                board = board
            )
        )
    }
}
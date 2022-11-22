package kakaostyle.pickMyItem.itempick.service

import kakaostyle.pickMyItem.itempick.domain.Board
import kakaostyle.pickMyItem.itempick.domain.Pick
import kakaostyle.pickMyItem.itempick.domain.Post
import kakaostyle.pickMyItem.itempick.domain.User
import kakaostyle.pickMyItem.itempick.dto.CreatePostInput
import kakaostyle.pickMyItem.itempick.dto.DeletePostInput
import kakaostyle.pickMyItem.itempick.dto.PostResponse
import kakaostyle.pickMyItem.itempick.repository.PostJpaRepository
import kakaostyle.pickMyItem.utils.OrderType
import kakaostyle.pickMyItem.utils.isNullOrFalse
import kakaostyle.pickMyItem.wishitem.dto.ItemInfoInput
import kotlin.math.round
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostService(
    private val postJpaRepository: PostJpaRepository,
    private val boardService: BoardService,
    private val userService: UserService,
) {
    val logger: Logger = LoggerFactory.getLogger(this::class.simpleName)

    companion object {
        private const val PAGE_SIZE = 10
    }

    @Transactional
    fun getPostBy(postId: Long): Post {
        return postJpaRepository.findPostById(postId).takeIf { it?.deleted.isNullOrFalse() }
            ?: throw RuntimeException("해당하는 게시글이 없습니다.")
    }

    @Transactional(readOnly = true)
    fun findPostResponseBy(postId: Long): PostResponse {
        return PostResponse.from(
            postJpaRepository.findPostById(postId).takeIf { it?.deleted.isNullOrFalse() }
                ?: throw RuntimeException("해당하는 게시글이 없습니다.")
        )
    }

    @Transactional(readOnly = true)
    fun getAllPostList(orderType: OrderType, userId: Long, page: Int = 0): List<PostResponse> {
        val user = userService.getUserBy(userId)
        val pickedPostIdList = getPickedPostIdList(user)

        return postJpaRepository.findAll(PageRequest.of(page, PAGE_SIZE))
            .filter { it.deleted.isNullOrFalse() }
            .sortedBy {
                when (orderType) {
                    OrderType.MOST_PICK -> -it.getTotalPickCount()
                    OrderType.MIN_PICK -> it.getTotalPickCount()
                    else -> 0
                }
            }
            .map { PostResponse.from(it, pickedPostIdList.contains(it.id)) }
    }

    private fun getPickedPostIdList(user: User): List<Long> {
        return user.myPickedPostList.mapNotNull { it.pickedPost?.id }
    }

    @Transactional(readOnly = true)
    fun getTotalPickCount(postId: Long): Int {
        return postJpaRepository.findPostById(postId).takeIf { it?.deleted.isNullOrFalse() }?.getTotalPickCount()
            ?: throw RuntimeException("해당하는 게시글이 없습니다.")
    }

    @Transactional
    fun createPost(input: CreatePostInput) {
        verify(input)
        val board = boardService.getBoardBy(input.boardId)
        val user = userService.getUserBy(input.userId)
        val post = savePost(input.postTitle, input.content ?: "", board, user)
        addPickItemToPost(input.itemInfoInputList, post)
        logger.info("POST 생성완료: $post.id, ${post.title}, ${post.content}")
    }

    private fun verify(input: CreatePostInput) {
        if (input.postTitle.length < 5 || input.postTitle.length > 30) throw RuntimeException("5자 이상 30자 이하로 제목을 작성해주세요.")
        if (input.content != null && input.content.length >= 100) throw RuntimeException("100자 이하로 본문을 작성해주세요.")
        if (input.itemInfoInputList.size < 2) throw RuntimeException("투표 생성을 위해 2개 이상의 상품을 등록해주세요.")
        if (input.itemInfoInputList.size > 4) throw RuntimeException("투표는 최대 4개의 상품까지 등록할 수 있습니다.")
    }

    private fun addPickItemToPost(itemList: List<ItemInfoInput>, post: Post) {
        itemList.forEach {
            post.savePick(
                Pick.from(it.itemId, it.brandName, it.itemName, it.itemImageUrl)
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
        val post = getPostBy(postId)
        val totalPickCount = post.getTotalPickCount()

        return post.pickList.map {
            PickResult(
                pickId = it.id,
                itemId = it.itemId,
                pickResult = if (totalPickCount == 0) 0.0 else round(it.pickCount.toDouble() / totalPickCount * 100)
            )
        }
    }

    @Transactional
    fun deletePost(deletePostInput: DeletePostInput) {
        val user = userService.getUserBy(deletePostInput.userId)
        val post = getPostBy(deletePostInput.postId)
        user.deleteMyPosting(post)
    }
}

data class PickResult(
    val pickId: Long,
    val itemId: Long,
    val pickResult: Double,
)
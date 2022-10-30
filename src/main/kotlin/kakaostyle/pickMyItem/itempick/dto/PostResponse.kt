package kakaostyle.pickMyItem.itempick.dto

import kakaostyle.pickMyItem.itempick.domain.Post
import kakaostyle.pickMyItem.wishitem.dto.ItemInfoInput

data class PostResponse(
    val postingUser: UserResponse,
    val postId: Long,
    val title: String,
    val content: String?,
    val pickList: List<PickResponse>
) {
    companion object {
        fun from(post: Post): PostResponse {
            val postingUser = UserResponse.from(post.postingUser!!)
            val pickResponseList = post.pickList.map { PickResponse.from(it) }
            return PostResponse(
                postingUser = postingUser,
                postId = post.id,
                title = post.title,
                content = post.content,
                pickList = pickResponseList
            )
        }
    }
}

data class PostList(
    val totalCount: Int,
    val itemList: List<PostResponse>
)

data class CreatePostInput(
    val postTitle: String,
    val content: String?,
    val itemInfoInputList: List<ItemInfoInput>,
    val boardId: Long,
    val userId: Long,
)

data class DeletePostInput(
    val userId: Long,
    val postId: Long,
)

package kakaostyle.pickMyItem.itempick.dto

import kakaostyle.pickMyItem.itempick.domain.Post

data class PostResponse(
    val postingUser: UserResponse,
    val pickedItemId: Long?,
    val postId: Long,
    val title: String,
    val content: String?,
    val pickList: List<PickResponse>,
    val totalPickCount: Int,
) {
    companion object {
        fun from(post: Post, pickedItemId: Long?): PostResponse {
            val postingUser = UserResponse.from(post.postingUser!!)
            val pickResponseList = post.pickList.map { PickResponse.from(it) }
            val totalPickCount = post.getTotalPickCount()
            return PostResponse(
                postingUser = postingUser,
                pickedItemId = pickedItemId,
                postId = post.id,
                title = post.title,
                content = post.content,
                pickList = pickResponseList,
                totalPickCount = totalPickCount,
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
    val itemIdList: List<Long>,
    val boardId: Long,
    val userId: Long,
)

data class DeletePostInput(
    val userId: Long,
    val postId: Long,
)

data class PostPickResult(
    val postId: Long,
    val pickResultList: List<PickResult>,
)

data class PostPickResultList(
    val totalCount: Int,
    val itemList: List<PostPickResult>,
)
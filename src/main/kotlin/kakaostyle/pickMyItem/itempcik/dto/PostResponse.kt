package kakaostyle.pickMyItem.post.dto

import kakaostyle.pickMyItem.pick.dto.PickResponse
import kakaostyle.pickMyItem.itempcik.domain.Post
import kakaostyle.pickMyItem.wishitem.dto.ItemInfoInput

data class PostResponse (
    val title: String,
    val content: String?,
    val pickList: List<PickResponse>
) {
    companion object {
        fun from(post: Post): PostResponse {
            val pickResponseList = post.pickList.map { PickResponse.from(it) }
            return PostResponse(
                title = post.title,
                content = post.content,
                pickList = pickResponseList
            )
        }
    }
}

data class CreatePostInput(
    val postTitle: String,
    val content: String?,
    val itemInfoInputList: List<ItemInfoInput>,
    val boardId: Long,
)

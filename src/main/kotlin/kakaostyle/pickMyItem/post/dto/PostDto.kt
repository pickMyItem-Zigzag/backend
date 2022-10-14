package kakaostyle.pickMyItem.post.dto

import kakaostyle.pickMyItem.post.domain.Post
import kakaostyle.pickMyItem.vote.dto.VoteDto

data class PostDto (
    val title: String,
    val content: String?,
    val voteList: List<VoteDto>
) {
    companion object {
        fun getPostDto(post: Post): PostDto {
            return PostDto(
                title = post.title,
                content = post.content,
                voteList = post.voteList.map { VoteDto.from(it) }
            )
        }
    }
}

data class SavePostInput(
    val postTitle: String,
    val content: String?,
    val voteList: List<VoteDto>
)
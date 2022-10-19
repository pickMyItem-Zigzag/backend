package kakaostyle.pickMyItem.itempcik.resolver

import graphql.kickstart.tools.GraphQLQueryResolver
import kakaostyle.pickMyItem.post.dto.PostResponse
import kakaostyle.pickMyItem.itempcik.service.PostService
import org.springframework.stereotype.Component

@Component
class PostQueryResolver(
    private val postService: PostService
) : GraphQLQueryResolver {

    fun getPost(postId: Long): PostResponse {
        return postService.getPost(postId)
    }

    fun getTotalPickCount(postId: Long): Int {
        return postService.getTotalPickCount(postId)
    }
}
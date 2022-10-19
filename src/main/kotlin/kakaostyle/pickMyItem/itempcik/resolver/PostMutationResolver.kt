package kakaostyle.pickMyItem.itempcik.resolver

import graphql.kickstart.tools.GraphQLMutationResolver
import kakaostyle.pickMyItem.post.dto.CreatePostInput
import kakaostyle.pickMyItem.itempcik.service.PostService
import org.springframework.stereotype.Component

@Component
class PostMutationResolver(
    private val postService: PostService
) : GraphQLMutationResolver {
    fun createPost(input: CreatePostInput): Boolean {
        postService.createPost(input)
        return true
    }
}
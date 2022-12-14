package kakaostyle.pickMyItem.itempick.resolver

import graphql.kickstart.tools.GraphQLMutationResolver
import kakaostyle.pickMyItem.itempick.dto.CreatePostInput
import kakaostyle.pickMyItem.itempick.dto.DeletePostInput
import kakaostyle.pickMyItem.itempick.service.PostService
import org.springframework.stereotype.Component

@Component
class PostMutationResolver(
    private val postService: PostService
) : GraphQLMutationResolver {
    fun createPost(input: CreatePostInput): Boolean {
        postService.createPost(input)
        return true
    }

    fun deletePost(input: DeletePostInput): Boolean {
        postService.deletePost(input)
        return true
    }
}
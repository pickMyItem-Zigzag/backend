package kakaostyle.pickMyItem.post.resolver

import kakaostyle.pickMyItem.post.dto.SavePostInput
import kakaostyle.pickMyItem.post.service.PostService
import org.springframework.graphql.data.method.annotation.MutationMapping

class PostMutationResolver(
    private val postService: PostService
) {

    @MutationMapping
    fun savePost(input: SavePostInput): Boolean {
        postService.savePost(input)
        return true
    }
}
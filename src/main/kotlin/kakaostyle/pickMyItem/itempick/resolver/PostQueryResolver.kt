package kakaostyle.pickMyItem.itempick.resolver

import graphql.kickstart.tools.GraphQLQueryResolver
import kakaostyle.pickMyItem.itempick.dto.PostList
import kakaostyle.pickMyItem.itempick.dto.PostResponse
import kakaostyle.pickMyItem.itempick.service.PickResult
import kakaostyle.pickMyItem.itempick.service.PostService
import org.springframework.stereotype.Component

@Component
class PostQueryResolver(
    private val postService: PostService
) : GraphQLQueryResolver {

    fun getPost(postId: Long): PostResponse {
        return postService.getPost(postId)
    }

    fun getPostList(): PostList {
        val allPostList = postService.getAllPostList()
        return PostList(
            allPostList.size,
            allPostList
        )
    }

    fun getTotalPickCount(postId: Long): Int {
        return postService.getTotalPickCount(postId)
    }

    fun getPickResult(postId: Long): List<PickResult> {
        return postService.getPickResult(postId)
    }
}
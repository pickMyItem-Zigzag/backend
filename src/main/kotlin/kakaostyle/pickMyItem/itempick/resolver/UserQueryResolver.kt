package kakaostyle.pickMyItem.itempick.resolver

import graphql.kickstart.tools.GraphQLQueryResolver
import kakaostyle.pickMyItem.itempick.dto.PickList
import kakaostyle.pickMyItem.itempick.dto.PostList
import kakaostyle.pickMyItem.itempick.dto.UserList
import kakaostyle.pickMyItem.itempick.dto.UserResponse
import kakaostyle.pickMyItem.itempick.service.UserService
import org.springframework.stereotype.Component

@Component
class UserQueryResolver(
    private val userService: UserService,
) : GraphQLQueryResolver {

    fun getUser(userId: Long): UserResponse {
        return userService.getUser(userId)
    }

    fun getUserList(): UserList {
        val allUserList = userService.getAllUserList()
        return UserList(
            allUserList.size,
            allUserList
        )
    }

    fun getMyPostList(userId: Long): PostList {
        val myPostList = userService.getMyPostList(userId)
        return PostList(
            myPostList.size,
            myPostList
        )
    }

    fun getMyPickList(userId: Long): PickList {
        val myPickList = userService.getMyPickList(userId)
        return PickList(
            myPickList.size,
            myPickList
        )
    }
}
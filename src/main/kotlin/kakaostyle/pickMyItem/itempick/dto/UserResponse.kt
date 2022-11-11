package kakaostyle.pickMyItem.itempick.dto

import kakaostyle.pickMyItem.itempick.domain.User

data class UserResponse(
    val userId: Long,
    val username: String,
    val age: Int,
    val profileImage: String,
) {
    companion object {
        fun from(user: User): UserResponse {
            return UserResponse(
                user.id,
                user.username,
                user.age,
                user.profileImage,
            )
        }
    }
}

data class UserList(
    val totalCount: Int,
    val itemList: List<UserResponse>
)
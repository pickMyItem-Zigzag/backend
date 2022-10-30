package kakaostyle.pickMyItem.itempick.dto

import kakaostyle.pickMyItem.itempick.domain.UserPost

data class UserPostResponse(
    val postId: Long
) {
    companion object {
        fun from(userPost: UserPost): UserPostResponse {
            return UserPostResponse(
                userPost.pickedPost!!.id
            )
        }
    }
}
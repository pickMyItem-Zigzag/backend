package kakaostyle.pickMyItem.itempick.service

import kakaostyle.pickMyItem.itempick.dto.UserPostResponse
import kakaostyle.pickMyItem.itempick.repository.UserPostJpaRepository
import kakaostyle.pickMyItem.utils.isNullOrFalse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserPostService(
    private val userPostJpaRepository: UserPostJpaRepository,
) {
    @Transactional(readOnly = true)
    fun existPostOfUserPicked(userId: Long, postId: Long): Boolean {
        val userPost = userPostJpaRepository.findUserPostByPickingUserIdAndPickedPostId(userId, postId)
        return userPost != null && userPost.deleted == false
    }

    @Transactional(readOnly = true)
    fun findAllPickedPostOfUser(userId: Long): List<UserPostResponse> {
        return userPostJpaRepository.findAllByPickingUserId(userId)
            .map { UserPostResponse.from(it) }
    }

    @Transactional(readOnly = true)
    fun findUserPost(userId: Long, postId: Long): UserPostResponse? {
        val userPost = userPostJpaRepository.findUserPostByPickingUserIdAndPickedPostId(userId, postId)
            .takeIf { it?.deleted.isNullOrFalse() } ?: return null

        return UserPostResponse.from(userPost)
    }
}
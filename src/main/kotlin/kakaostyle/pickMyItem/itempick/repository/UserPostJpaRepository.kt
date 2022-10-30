package kakaostyle.pickMyItem.itempick.repository

import kakaostyle.pickMyItem.itempick.domain.UserPost
import org.springframework.data.jpa.repository.JpaRepository

interface UserPostJpaRepository : JpaRepository<UserPost, Long> {
    fun findUserPostByPickingUserIdAndPickedPostId(userId: Long, postId: Long): UserPost?

    fun findAllByPickingUserId(userId: Long): List<UserPost>
}
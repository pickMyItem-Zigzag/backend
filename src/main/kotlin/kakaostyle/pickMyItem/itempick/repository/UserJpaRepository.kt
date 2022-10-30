package kakaostyle.pickMyItem.itempick.repository

import kakaostyle.pickMyItem.itempick.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserJpaRepository : JpaRepository<User, Long> {
    fun findUserById(userId: Long): User?
}
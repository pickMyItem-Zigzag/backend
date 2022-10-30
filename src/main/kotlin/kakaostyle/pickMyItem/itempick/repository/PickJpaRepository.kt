package kakaostyle.pickMyItem.itempick.repository

import kakaostyle.pickMyItem.itempick.domain.Pick
import org.springframework.data.jpa.repository.JpaRepository

interface PickJpaRepository : JpaRepository<Pick, Long> {
    fun findByPostIdAndItemId(postId: Long, itemId: Long): Pick?
}
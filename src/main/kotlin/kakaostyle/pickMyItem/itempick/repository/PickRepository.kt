package kakaostyle.pickMyItem.itempick.repository

import kakaostyle.pickMyItem.itempick.domain.Pick
import org.springframework.data.jpa.repository.JpaRepository

interface PickRepository : JpaRepository<Pick, Long> {
    fun findByPostIdAndItemId(id: Long, itemId: Long): Pick?
}
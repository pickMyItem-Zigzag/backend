package kakaostyle.pickMyItem.pick.repository

import kakaostyle.pickMyItem.pick.domain.Pick
import org.springframework.data.jpa.repository.JpaRepository

interface PickRepository : JpaRepository<Pick, Long>
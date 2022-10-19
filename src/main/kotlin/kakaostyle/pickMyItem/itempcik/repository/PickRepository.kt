package kakaostyle.pickMyItem.itempcik.repository

import kakaostyle.pickMyItem.itempcik.domain.Pick
import org.springframework.data.jpa.repository.JpaRepository

interface PickRepository : JpaRepository<Pick, Long>
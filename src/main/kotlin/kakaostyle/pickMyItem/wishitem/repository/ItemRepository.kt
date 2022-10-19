package kakaostyle.pickMyItem.wishitem.repository

import kakaostyle.pickMyItem.wishitem.domain.Item
import org.springframework.data.jpa.repository.JpaRepository

interface ItemRepository : JpaRepository<Item, Long>
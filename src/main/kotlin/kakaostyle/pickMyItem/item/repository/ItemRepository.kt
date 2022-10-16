package kakaostyle.pickMyItem.item.repository

import kakaostyle.pickMyItem.item.domain.Item
import org.springframework.data.jpa.repository.JpaRepository

interface ItemRepository : JpaRepository<Item, Long>
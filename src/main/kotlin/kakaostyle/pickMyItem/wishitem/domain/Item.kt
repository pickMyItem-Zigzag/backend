package kakaostyle.pickMyItem.wishitem.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import kakaostyle.pickMyItem.base.Base

@Entity
class Item(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0L,
    @Column var brandName: String,
    @Column var itemName: String,
    @Column var itemImageUrl: String,
    @Column var originPrice: Int,
) : Base()
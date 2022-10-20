package kakaostyle.pickMyItem.itempick.domain

import kakaostyle.pickMyItem.base.Base
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import kakaostyle.pickMyItem.wishitem.dto.ItemInfoInput

@Entity
class Pick(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0L,
    @Column var pickCount: Int,
    @Column var itemName: String,
    @Column var itemImageUrl: String?,
    @Column(nullable = false) var itemId: Long = 0L,
    @ManyToOne @JoinColumn(name = "post_id")
    var post: Post? = null,
) : Base() {

    companion object {
        fun from(itemInfoInput: ItemInfoInput): Pick {
            return Pick(
                pickCount = 0,
                itemId = itemInfoInput.itemId,
                itemName = itemInfoInput.itemName,
                itemImageUrl = itemInfoInput.itemImageUrl
            )
        }
    }

    fun pickThis() {
        synchronized(this) { ++this.pickCount }
    }

    fun unPickThis() {
        synchronized(this) {
            if (this.pickCount > 0) --this.pickCount
            else throw RuntimeException("각 pick은 0 미만의 특표수를 가질 수 없습니다.")
        }
    }
}
package kakaostyle.pickMyItem.itempick.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import kakaostyle.pickMyItem.base.Base
import org.springframework.transaction.annotation.Transactional

@Entity
class Pick(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0L,
    @Column var pickCount: Int,
    @Column var itemName: String,
    @Column var brandName: String,
    @Column var itemImageUrl: String?,
    @Column(nullable = false) var itemId: Long = 0L,
    @ManyToOne @JoinColumn(name = "post_id")
    var post: Post? = null,
    @ManyToOne @JoinColumn(name = "user_id")
    var user: User? = null,
) : Base() {

    companion object {
        fun from(
            itemId: Long,
            brandName: String,
            itemName: String,
            itemImageUrl: String,
        ): Pick {
            return Pick(
                pickCount = 0,
                itemId = itemId,
                brandName = brandName,
                itemName = itemName,
                itemImageUrl = itemImageUrl,
            )
        }
    }

    @Transactional
    fun pickThis(user: User) {
        synchronized(this) {
            ++this.pickCount
        }
    }

    @Transactional
    fun unPickThis(user: User) {
        synchronized(this) {
            if (this.pickCount > 0) {
                --this.pickCount
            } else throw RuntimeException("각 pick은 0 미만의 특표수를 가질 수 없습니다.")
        }
    }
}
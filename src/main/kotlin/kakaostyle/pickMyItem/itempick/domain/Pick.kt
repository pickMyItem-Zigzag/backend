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
    @Column(nullable = false) var itemId: Long = 0L,
    @ManyToOne @JoinColumn(name = "post_id")
    var post: Post? = null,
) : Base() {

    companion object {
        fun from(
            itemId: Long,
        ): Pick {
            return Pick(
                pickCount = 0,
                itemId = itemId
            )
        }
    }

    @Transactional
    fun pickThis(user: User, post: Post) {
        synchronized(this) {
            user.addPickedPostList(post, this.itemId)
            ++this.pickCount
            ++post.pickCount
        }
    }

    @Transactional
    fun unPickThis(user: User, post: Post) {
        synchronized(this) {
            if (this.pickCount > 0) {
                user.deletePickedPostList(post)
                --this.pickCount
                --post.pickCount
            } else throw RuntimeException("각 pick은 0 미만의 특표수를 가질 수 없습니다.")
        }
    }
}
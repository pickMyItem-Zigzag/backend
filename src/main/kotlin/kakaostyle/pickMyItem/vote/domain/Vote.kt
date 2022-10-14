package kakaostyle.pickMyItem.vote.domain

import kakaostyle.pickMyItem.base.Base
import kakaostyle.pickMyItem.post.domain.Post
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class Vote(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
    @Column var pickCount: Int,
    @Column var imageUrl: String?,
    @Column var comment: String?,
    @ManyToOne @JoinColumn(name = "post_id")
    var post: Post,
    @Column var deleted: Boolean,
) : Base() {

    fun pickThis() {
        synchronized(this) { ++this.pickCount }
    }

    fun unPickThis() {
        synchronized(this) { --this.pickCount }
    }


}
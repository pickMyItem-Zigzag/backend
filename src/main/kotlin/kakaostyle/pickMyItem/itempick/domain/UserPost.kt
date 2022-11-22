package kakaostyle.pickMyItem.itempick.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import kakaostyle.pickMyItem.base.Base

@Entity
class UserPost(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0L,
    @ManyToOne @JoinColumn(name = "picked_user_id")
    var pickingUser: User? = null,
    @ManyToOne @JoinColumn(name = "picked_post_id")
    var pickedPost: Post? = null,
    @Column val itemId: Long,
) : Base()
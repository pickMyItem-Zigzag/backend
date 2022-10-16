package kakaostyle.pickMyItem.post.domain

import kakaostyle.pickMyItem.base.Base
import kakaostyle.pickMyItem.board.domain.Board
import kakaostyle.pickMyItem.pick.domain.Pick
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity
class Post(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0L,
    @Column(nullable = false) var title: String,
    @Column(length = 3000) var content: String?,
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    var pickList: MutableList<Pick> = mutableListOf(),
    @ManyToOne @JoinColumn(name = "post_id")
    var board: Board,
) : Base() {

    fun getTotalPickCount(): Int {
        return pickList.sumOf { it.pickCount }
    }

    fun addPick(pick: Pick) {
        pick.post = this
        this.pickList.add(pick)
    }
}
package kakaostyle.pickMyItem.post.domain

import kakaostyle.pickMyItem.base.Base
import kakaostyle.pickMyItem.board.domain.Board
import kakaostyle.pickMyItem.vote.domain.Vote
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
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
    @Column var title: String,
    @Column(length = 3000) var content: String?,
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    var voteList: MutableList<Vote> = mutableListOf(),
    @ManyToOne @JoinColumn(name = "post_id")
    var board: Board,
    @Column var deleted: Boolean,
) : Base() {

    fun getTotalPickCount(): Int {
        return voteList.sumOf { it.pickCount }
    }

    fun addVote(vote: Vote) {
        this.voteList.add(vote)
        vote.post = this
    }
}
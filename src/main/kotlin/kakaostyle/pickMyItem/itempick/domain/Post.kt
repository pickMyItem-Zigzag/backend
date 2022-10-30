package kakaostyle.pickMyItem.itempick.domain

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
import kakaostyle.pickMyItem.base.Base
import org.springframework.transaction.annotation.Transactional

@Entity
class Post(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0L,
    @Column(nullable = false) var title: String,
    @Column(length = 3000) var content: String?,
    @OneToMany(
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
        fetch = FetchType.LAZY,
        mappedBy = "post"
    )
    var pickList: MutableList<Pick> = mutableListOf(),
    @ManyToOne @JoinColumn(name = "board_id")
    var board: Board? = null,
    @ManyToOne @JoinColumn(name = "posting_user_id")
    var postingUser: User? = null,
) : Base() {

    @Transactional(readOnly = true)
    fun getTotalPickCount(): Int {
        return pickList.sumOf { it.pickCount }
    }

    @Transactional
    fun savePick(pick: Pick) {
        pick.post = this
        this.pickList.add(pick)
    }
}
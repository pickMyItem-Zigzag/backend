package kakaostyle.pickMyItem.board.domain

import kakaostyle.pickMyItem.base.Base
import kakaostyle.pickMyItem.post.domain.Post
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class Board(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
    @Column var boardName: String,
    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = false, mappedBy = "board")
    val postList: MutableList<Post> = mutableListOf(),
    @Column var deleted: Boolean? = null,
) : Base(){

    fun addPost(post: Post) {
        this.postList.add(post)
        post.board = this
    }
}
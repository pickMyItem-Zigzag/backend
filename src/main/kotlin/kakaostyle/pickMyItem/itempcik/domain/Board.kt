package kakaostyle.pickMyItem.itempcik.domain

import kakaostyle.pickMyItem.base.Base
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
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0L,
    @Column var boardName: String,
    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = false, mappedBy = "board")
    val postList: MutableList<Post> = mutableListOf(),
) : Base(){

    fun addPost(post: Post) {
        post.board = this
        this.postList.add(post)
    }
}
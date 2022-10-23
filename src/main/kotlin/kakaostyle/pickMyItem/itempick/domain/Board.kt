package kakaostyle.pickMyItem.itempick.domain

import kakaostyle.pickMyItem.base.Base
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import org.springframework.transaction.annotation.Transactional

@Entity
class Board(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0L,
    @Column var boardName: String,
    @OneToMany(
        cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY,
        orphanRemoval = true,
        mappedBy = "board"
    )
    val postList: MutableList<Post> = mutableListOf(),
) : Base(){

    @Transactional
    fun addPost(post: Post): Post {
        post.board = this
        this.postList.add(post)
        return post
    }
}
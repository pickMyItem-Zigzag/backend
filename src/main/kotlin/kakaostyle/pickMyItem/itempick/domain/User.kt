package kakaostyle.pickMyItem.itempick.domain

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import kakaostyle.pickMyItem.base.Base

@Entity
class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0L,
    @Column(nullable = false) var username: String,
    @Column(nullable = false) var age: Int,
    @Column(nullable = false) var profileImage: String,
    @OneToMany(
        cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY,
        mappedBy = "postingUser"
    )
    val myPostingList: MutableList<Post> = mutableListOf(),
    @OneToMany(
        cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY,
        mappedBy = "pickingUser"
    )
    val myPickedPostList: MutableList<UserPost> = mutableListOf(),
) : Base() {

    fun addMyPostingList(post: Post) {
        post.postingUser = this
        this.myPostingList.add(post)
    }

    fun deleteMyPosting(post: Post) {
        if (myPostingList.contains(post)) {
            post.postingUser = null
            post.deleted = true
        } else throw RuntimeException("본인이 작성한 게시글만 지울 수 있습니다.")
    }

    fun addPickedPostList(post: Post, itemId: Long) {
        val userPost = UserPost(pickingUser = this, pickedPost = post, itemId = itemId)
        userPost.deleted = false
        myPickedPostList.add(userPost)
    }

    fun deletePickedPostList(post: Post) {
        myPickedPostList.first { it.pickingUser == this && it.pickedPost == post }.deleted = true
    }
}
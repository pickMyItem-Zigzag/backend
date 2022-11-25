package kakaostyle.pickMyItem.itempick.repository

import kakaostyle.pickMyItem.itempick.domain.Post
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface PostJpaRepository : JpaRepository<Post, Long> {

    fun findPostById(postId: Long): Post?
    fun findAllByPostingUserId(userId: Long): List<Post>

    fun findAllByDeletedNullOrDeletedFalse(pageable: Pageable): List<Post>

    fun findAllByDeletedNullOrDeletedFalseOrderByTotalPickCount(pageable: Pageable): List<Post>
}
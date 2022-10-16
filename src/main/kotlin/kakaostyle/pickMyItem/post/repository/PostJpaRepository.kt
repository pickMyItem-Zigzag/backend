package kakaostyle.pickMyItem.post.repository

import kakaostyle.pickMyItem.post.domain.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostJpaRepository : JpaRepository<Post, Long>
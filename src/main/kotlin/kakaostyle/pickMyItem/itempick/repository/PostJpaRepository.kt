package kakaostyle.pickMyItem.itempick.repository

import kakaostyle.pickMyItem.itempick.domain.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostJpaRepository : JpaRepository<Post, Long>
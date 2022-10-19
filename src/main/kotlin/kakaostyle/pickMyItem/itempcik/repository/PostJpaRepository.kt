package kakaostyle.pickMyItem.itempcik.repository

import kakaostyle.pickMyItem.itempcik.domain.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostJpaRepository : JpaRepository<Post, Long>
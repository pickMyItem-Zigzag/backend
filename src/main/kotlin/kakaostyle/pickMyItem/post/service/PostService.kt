package kakaostyle.pickMyItem.post.service

import kakaostyle.pickMyItem.post.domain.Post
import kakaostyle.pickMyItem.post.dto.SavePostInput
import kakaostyle.pickMyItem.post.repository.PostJpaRepository
import kakaostyle.pickMyItem.vote.domain.Vote
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostService(
    private val postJpaRepository: PostJpaRepository
) {
    @Transactional
    fun savePost(input: SavePostInput) {
        postJpaRepository.save(
            Post(
                title = input.postTitle,
                content = input.content,
                voteList = input.voteList.map { Vote(it.) }
            )
        )
    }
}
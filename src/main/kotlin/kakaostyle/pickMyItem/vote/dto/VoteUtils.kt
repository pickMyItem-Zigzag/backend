package kakaostyle.pickMyItem.vote.dto

import kakaostyle.pickMyItem.vote.domain.Vote

data class VoteDto (
    val pickCount: Int,
    val imageUrl: String?,
    val comment: String?,
) {
    companion object {
        fun from(vote: Vote): VoteDto {
            return VoteDto(
                vote.pickCount,
                vote.imageUrl,
                vote.comment
            )
        }
    }
}


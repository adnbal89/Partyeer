package com.partyeer.domain.repository.party.model

import android.text.format.DateFormat
import com.partyeer.domain.repository.base.model.BaseItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class Party(
    val id: String,
    val logoUrl: String?,
    val title: String,
    val concept: Concept,
    var timeStart: Long,
    var timeEnd: Long,
    val description: String?,
    val pictures: MutableList<Picture>,
    var likeCount: Int = 0,
    var entranceFee: String,
    var inviteeList: HashMap<String, Boolean> = HashMap(),
    var likedUserIdList: HashMap<String, Boolean> = HashMap(),
    var appliedUserIdList: HashMap<String, Boolean> = HashMap(),
    val creatorUserId: String,
    val address: Address = Address(),
    var tagList: HashMap<String, Boolean> = HashMap(),
) : BaseItem() {
    val formattedDate: CharSequence
        get() = DateFormat.format(" dd/MM HH:mm", timeStart)

}


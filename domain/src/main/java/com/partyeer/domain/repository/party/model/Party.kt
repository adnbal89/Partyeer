package com.partyeer.domain.repository.party.model

import com.partyeer.domain.repository.base.model.BaseItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class Party(
    val id: String,
    val logoUrl: String?,
    val title: String,
    val concept: Concept,
    val longitude: Double,
    val latitude: Double,
    var timeStart: Long,
    var timeEnd: Long,
    val description: String?,
    val pictures: MutableList<Picture>,
    var likeCount: Int = 0,
    var inviteeList: HashMap<String, Boolean> = HashMap(),
    var likedUserIdList: HashMap<String, Boolean> = HashMap(),
    var appliedUserIdList: HashMap<String, Boolean> = HashMap(),
    val creatorUserId: String,
    var address: String = "Party Address"
) : BaseItem()


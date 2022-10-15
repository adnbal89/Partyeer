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
    var inviteeList: MutableMap<String, Boolean> = HashMap(),
    var likedUserIdList: MutableMap<String, Boolean> = HashMap(),
    var appliedUserIdList: MutableMap<String, Boolean> = HashMap(),
    val creatorUserId: String,
) : BaseItem()


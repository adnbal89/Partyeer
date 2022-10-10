package com.partyeer.data.local.model

import com.partyeer.data.base.local.model.BaseEntity

data class PartyEntity(
    val id: String,
    val logoUrl: String?,
    val title: String,
    val concept: ConceptEntity,
    val longitude: Double,
    val latitude: Double,
    var timeStart: Long,
    var timeEnd: Long,
    val description: String?,
    val pictures: MutableList<PictureEntity>,
    var likeCount: Int = 0,
    var inviteeList: MutableList<String>,
    var likedUserIdList: MutableList<String>,
    var appliedUserIdList: MutableMap<String,Boolean> = HashMap(),
    val creatorUserId: String,
) : BaseEntity()

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
    var entranceFee: String,
    val pictures: MutableList<PictureEntity>,
    var likeCount: Int = 0,
    var inviteeList: HashMap<String, Boolean> = HashMap(),
    var likedUserIdList: HashMap<String, Boolean> = HashMap(),
    var appliedUserIdList: HashMap<String, Boolean> = HashMap(),
    val creatorUserId: String,
    val tagList: HashMap<String, Boolean> = HashMap()
) : BaseEntity()

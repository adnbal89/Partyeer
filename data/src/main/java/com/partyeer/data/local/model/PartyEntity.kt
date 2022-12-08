package com.partyeer.data.local.model

import com.partyeer.data.base.local.model.BaseEntity

data class PartyEntity(
    val id: String,
    val logoUrl: String?,
    val title: String,
    val concept: ConceptEntity,
    var timeStart: Long,
    var timeEnd: Long,
    val description: String?,
    var entranceFee: String,
    val pictures: MutableList<PictureEntity>,
    var likeCount: Int = 0,
    var inviteeMap: HashMap<String, Boolean> = HashMap(),
    var likedUserIdMap: HashMap<String, Boolean> = HashMap(),
    var appliedUserIdMap: HashMap<String, Boolean> = HashMap(),
    val address: AddressEntity,
    val tagMap: HashMap<String, Boolean> = HashMap(),
    val partyCreatorUser: PartyCreatorUserEntity = PartyCreatorUserEntity()
) : BaseEntity()

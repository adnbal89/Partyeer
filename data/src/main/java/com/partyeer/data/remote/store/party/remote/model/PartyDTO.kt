package com.partyeer.data.remote.store.party.remote.model

import com.partyeer.data.base.remote.model.BaseResponse

data class PartyDTO(
    val id: String = "",
    var logoUrl: String? = "",
    val title: String = "",
    val concept: ConceptDTO = ConceptDTO(""),
    val longitude: Double = 0.0,
    val latitude: Double = 0.0,
    val timeStart: Long = 0,
    var timeEnd: Long = 0,
    val description: String? = "",
    val pictures: MutableList<PictureDTO> = mutableListOf(),
    var likeCount: Int = 0,
    var inviteeList: HashMap<String, Boolean> = HashMap(),
    var likedUserIdList: HashMap<String, Boolean> = HashMap(),
    var appliedUserIdList: HashMap<String, Boolean> = HashMap(),
    val creatorUserId: String = "",
) : BaseResponse()
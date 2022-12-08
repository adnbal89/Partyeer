package com.partyeer.data.remote.store.party.remote.model

import com.partyeer.data.base.remote.model.BaseResponse
import com.partyeer.data.remote.store.user.remote.model.PartyCreatorUserDTO

data class PartyDTO(
    val id: String = "",
    var logoUrl: String? = "",
    val title: String = "",
    val concept: ConceptDTO = ConceptDTO(""),
    val timeStart: Long = 0,
    var timeEnd: Long = 0,
    val description: String? = "",
    var entranceFee: String = "",
    val pictures: MutableList<PictureDTO> = mutableListOf(),
    var likeCount: Int = 0,
    var inviteeMap: HashMap<String, Boolean> = HashMap(),
    var likedUserIdMap: HashMap<String, Boolean> = HashMap(),
    var appliedUserIdMap: HashMap<String, Boolean> = HashMap(),
    val address: AddressDTO = AddressDTO(),
    val tagMap: HashMap<String, Boolean> = HashMap(),
    val partyCreatorUser: PartyCreatorUserDTO = PartyCreatorUserDTO()
) : BaseResponse()
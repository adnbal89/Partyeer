package com.partyeer.domain.repository.party.model

import android.text.format.DateFormat
import com.partyeer.domain.repository.base.model.BaseItem
import com.partyeer.domain.repository.user.model.PartyCreatorUser
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
    var inviteeMap: HashMap<String, Boolean> = HashMap(),
    var likedUserIdMap: HashMap<String, Boolean> = HashMap(),
    var appliedUserIdMap: HashMap<String, Boolean> = HashMap(),
    val address: Address = Address(),
    var tagMap: HashMap<String, Boolean> = HashMap(),
    val partyCreatorUser: PartyCreatorUser = PartyCreatorUser()
) : BaseItem() {
    val formattedDate: CharSequence
        get() = DateFormat.format(" dd/MM HH:mm", timeStart)

    val detailedTitle: CharSequence
        get() = "${title}, ${address.subAdminArea}"

}


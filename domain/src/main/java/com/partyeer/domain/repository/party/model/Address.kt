package com.partyeer.domain.repository.party.model

import com.partyeer.domain.repository.base.model.BaseItem
import kotlinx.parcelize.Parcelize

@Parcelize
class Address(
    var featureName: String? = "",
    val adminArea: String? = "",
    val subAdminArea: String? = "",
    val locality: String? = "",
    val subLocality: String? = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val addressLine: String? = ""
) : BaseItem()
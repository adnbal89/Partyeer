package com.partyeer.data.remote.store.party.remote.model

import com.partyeer.data.base.remote.model.BaseResponse

class AddressDTO(
    var featureName: String? = "",
    val adminArea: String? = "",
    val subAdminArea: String? = "",
    val locality: String? = "",
    val subLocality: String? = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val addressLine: String? = ""
) : BaseResponse()
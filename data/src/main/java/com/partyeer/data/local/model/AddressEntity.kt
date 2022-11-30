package com.partyeer.data.local.model

import com.partyeer.data.base.local.model.BaseEntity

class AddressEntity(
    var featureName: String? = "",
    val adminArea: String? = "",
    val subAdminArea: String? = "",
    val locality: String? = "",
    val subLocality: String? = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val addressLine: String? = ""
) : BaseEntity()
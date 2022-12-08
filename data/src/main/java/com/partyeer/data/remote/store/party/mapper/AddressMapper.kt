package com.partyeer.data.remote.store.party.mapper

import com.partyeer.data.base.mapper.BaseMapper
import com.partyeer.data.remote.store.party.remote.model.AddressDTO
import com.partyeer.domain.repository.party.model.Address
import javax.inject.Singleton

@Singleton
class AddressMapper : BaseMapper<AddressDTO, Address> {

    override fun map(source: AddressDTO, vararg extra: Any?): Address {
        return Address(
            featureName = source.featureName,
            adminArea = source.adminArea,
            subAdminArea = source.subAdminArea,
            locality = source.locality,
            subLocality = source.subLocality,
            latitude = source.latitude,
            longitude = source.longitude,
            addressLine = source.addressLine
        )
    }
}
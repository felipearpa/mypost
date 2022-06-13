package com.pipel.mypost.address.mapper

import com.pipel.mypost.address.data.AddressResponse
import com.pipel.mypost.address.domain.Address
import com.pipel.mypost.address.view.AddressModel
import com.pipel.mypost.geo.mapper.GeoMapper
import com.pipel.mypost.type.NonEmptyString

object AddressMapper {

    fun mapFromDataToDomain(addressResponse: AddressResponse): Address =
        Address(
            street = NonEmptyString(addressResponse.street),
            suite = NonEmptyString(addressResponse.suite),
            city = NonEmptyString(addressResponse.city),
            zipcode = NonEmptyString(addressResponse.zipcode),
            geo = GeoMapper.mapFromDataToDomain(addressResponse.geo)
        )

    fun mapFromDomainToView(address: Address): AddressModel =
        AddressModel(
            street = address.street.value,
            suite = address.suite.value,
            city = address.city.value,
            zipcode = address.zipcode.value,
            geo = GeoMapper.mapFromDomainToView(address.geo)
        )

}
package com.felipearpa.mypost.address.mapper

import com.felipearpa.mypost.address.data.AddressResponse
import com.felipearpa.mypost.address.domain.Address
import com.felipearpa.mypost.address.view.AddressModel
import com.felipearpa.mypost.geo.mapper.GeoMapper
import com.felipearpa.mypost.type.NonEmptyString

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
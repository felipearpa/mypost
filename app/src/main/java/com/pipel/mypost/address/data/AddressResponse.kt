package com.pipel.mypost.address.data

import com.pipel.mypost.geo.data.GeoPointResponse

data class AddressResponse(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: GeoPointResponse
)
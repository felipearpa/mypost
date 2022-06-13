package com.pipel.mypost.address.view

import com.pipel.mypost.geo.view.GeoPointModel

data class AddressModel(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: GeoPointModel
)
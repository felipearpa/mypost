package com.felipearpa.mypost.address.view

import com.felipearpa.mypost.geo.view.GeoPointModel

data class AddressModel(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: GeoPointModel
)
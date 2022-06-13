package com.felipearpa.mypost.address.domain

import com.felipearpa.mypost.geo.domain.GeoPoint
import com.felipearpa.mypost.type.NonEmptyString

data class Address(
    val street: NonEmptyString,
    val suite: NonEmptyString,
    val city: NonEmptyString,
    val zipcode: NonEmptyString,
    val geo: GeoPoint
)
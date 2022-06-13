package com.pipel.mypost.address.domain

import com.pipel.mypost.geo.domain.GeoPoint
import com.pipel.mypost.type.NonEmptyString

data class Address(
    val street: NonEmptyString,
    val suite: NonEmptyString,
    val city: NonEmptyString,
    val zipcode: NonEmptyString,
    val geo: GeoPoint
)
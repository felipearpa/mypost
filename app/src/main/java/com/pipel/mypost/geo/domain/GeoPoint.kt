package com.pipel.mypost.geo.domain

import com.pipel.mypost.type.NonEmptyString

data class GeoPoint(
    val latitude: NonEmptyString,
    val longitude: NonEmptyString
)
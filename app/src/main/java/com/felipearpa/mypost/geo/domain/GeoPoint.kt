package com.felipearpa.mypost.geo.domain

import com.felipearpa.mypost.type.NonEmptyString

data class GeoPoint(
    val latitude: NonEmptyString,
    val longitude: NonEmptyString
)
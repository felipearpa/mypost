package com.felipearpa.mypost.geo.mapper

import com.felipearpa.mypost.geo.data.GeoPointResponse
import com.felipearpa.mypost.geo.domain.GeoPoint
import com.felipearpa.mypost.geo.view.GeoPointModel
import com.felipearpa.mypost.type.NonEmptyString

object GeoMapper {

    fun mapFromDataToDomain(geoPointResponse: GeoPointResponse): GeoPoint =
        GeoPoint(
            latitude = NonEmptyString(geoPointResponse.lat),
            longitude = NonEmptyString(geoPointResponse.lng)
        )

    fun mapFromDomainToView(geoPoint: GeoPoint): GeoPointModel =
        GeoPointModel(
            latitude = geoPoint.latitude.value,
            longitude = geoPoint.longitude.value
        )

}
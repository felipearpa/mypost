package com.pipel.mypost.geo.mapper

import com.pipel.mypost.geo.data.GeoPointResponse
import com.pipel.mypost.geo.domain.GeoPoint
import com.pipel.mypost.geo.view.GeoPointModel
import com.pipel.mypost.type.NonEmptyString

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
package com.pipel.mypost.user.mapper

import com.pipel.mypost.address.mapper.AddressMapper
import com.pipel.mypost.company.mapper.CompanyMapper
import com.pipel.mypost.type.Identifier
import com.pipel.mypost.type.NonEmptyString
import com.pipel.mypost.user.data.UserResponse
import com.pipel.mypost.user.domain.User
import com.pipel.mypost.user.view.UserModel

object UserMapper {

    fun mapFromDataToDomain(userResponse: UserResponse): User =
        User(
            id = Identifier(userResponse.id),
            name = NonEmptyString(userResponse.name),
            userName = if (userResponse.userName != null) NonEmptyString(userResponse.userName) else null,
            email = NonEmptyString(userResponse.email),
            address = AddressMapper.mapFromDataToDomain(userResponse.address),
            phone = NonEmptyString(userResponse.phone),
            website = NonEmptyString(userResponse.website),
            company = CompanyMapper.mapFromDataToDomain(userResponse.company)
        )

    fun mapFromDomainToView(user: User): UserModel =
        UserModel(
            id = user.id.value,
            name = user.name.value,
            userName = user.userName?.value,
            email = user.email.value,
            address = AddressMapper.mapFromDomainToView(user.address),
            phone = user.phone.value,
            website = user.website.value,
            company = CompanyMapper.mapFromDomainToView(user.company)
        )

}
package com.felipearpa.mypost.user.mapper

import com.felipearpa.mypost.address.mapper.AddressMapper
import com.felipearpa.mypost.company.mapper.CompanyMapper
import com.felipearpa.mypost.type.Email
import com.felipearpa.mypost.type.Identifier
import com.felipearpa.mypost.type.NonEmptyString
import com.felipearpa.mypost.user.data.UserResponse
import com.felipearpa.mypost.user.domain.User
import com.felipearpa.mypost.user.view.UserModel

object UserMapper {

    fun mapFromDataToDomain(userResponse: UserResponse): User =
        User(
            id = Identifier(userResponse.id),
            name = NonEmptyString(userResponse.name),
            userName = if (userResponse.userName != null) NonEmptyString(userResponse.userName) else null,
            email = Email(userResponse.email),
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
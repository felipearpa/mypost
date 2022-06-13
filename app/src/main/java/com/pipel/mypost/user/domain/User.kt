package com.pipel.mypost.user.domain

import com.pipel.mypost.address.domain.Address
import com.pipel.mypost.company.domain.Company
import com.pipel.mypost.type.Identifier
import com.pipel.mypost.type.NonEmptyString

data class User(
    val id: Identifier,
    val name: NonEmptyString,
    val userName: NonEmptyString?,
    val email: NonEmptyString,
    val address: Address,
    val phone: NonEmptyString,
    val website: NonEmptyString,
    val company: Company
)
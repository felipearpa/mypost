package com.felipearpa.mypost.user.domain

import com.felipearpa.mypost.address.domain.Address
import com.felipearpa.mypost.company.domain.Company
import com.felipearpa.mypost.type.Email
import com.felipearpa.mypost.type.Identifier
import com.felipearpa.mypost.type.NonEmptyString

data class User(
    val id: Identifier,
    val name: NonEmptyString,
    val userName: NonEmptyString?,
    val email: Email,
    val address: Address,
    val phone: NonEmptyString,
    val website: NonEmptyString,
    val company: Company
)
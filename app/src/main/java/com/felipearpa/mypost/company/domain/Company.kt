package com.felipearpa.mypost.company.domain

import com.felipearpa.mypost.type.NonEmptyString

data class Company(
    val name: NonEmptyString,
    val catchPhrase: NonEmptyString,
    val bs: NonEmptyString
)
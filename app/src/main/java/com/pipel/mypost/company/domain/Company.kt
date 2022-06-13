package com.pipel.mypost.company.domain

import com.pipel.mypost.type.NonEmptyString

data class Company(
    val name: NonEmptyString,
    val catchPhrase: NonEmptyString,
    val bs: NonEmptyString
)
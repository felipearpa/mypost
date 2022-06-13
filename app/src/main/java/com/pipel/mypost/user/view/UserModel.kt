package com.pipel.mypost.user.view

import com.pipel.mypost.address.view.AddressModel
import com.pipel.mypost.company.view.CompanyModel

data class UserModel(
    val id: Int,
    val name: String,
    val userName: String?,
    val email: String,
    val address: AddressModel,
    val phone: String,
    val website: String,
    val company: CompanyModel
)
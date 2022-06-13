package com.felipearpa.mypost.user.view

import com.felipearpa.mypost.address.view.AddressModel
import com.felipearpa.mypost.company.view.CompanyModel

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
package com.felipearpa.mypost.user.data

import com.felipearpa.mypost.address.data.AddressResponse
import com.felipearpa.mypost.company.data.CompanyResponse

data class UserResponse(
    val id: Int,
    val name: String,
    val userName: String?,
    val email: String,
    val address: AddressResponse,
    val phone: String,
    val website: String,
    val company: CompanyResponse
)
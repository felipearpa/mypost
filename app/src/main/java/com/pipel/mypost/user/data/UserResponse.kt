package com.pipel.mypost.user.data

import com.pipel.mypost.address.data.AddressResponse
import com.pipel.mypost.company.data.CompanyResponse

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
package com.felipearpa.mypost.user.useCase

import com.felipearpa.mypost.user.data.UserRepository
import com.felipearpa.mypost.user.domain.User
import com.felipearpa.mypost.user.mapper.UserMapper
import javax.inject.Inject

interface GetUserUseCase {

    suspend fun execute(userId: Int): User

}

class DefaultGetUserUseCase @Inject constructor(private val userRepository: UserRepository) :
    GetUserUseCase {

    override suspend fun execute(userId: Int): User =
        UserMapper.mapFromDataToDomain(userRepository.getUser(userId = userId))

}
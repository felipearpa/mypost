package com.pipel.mypost.user.useCase

import com.pipel.mypost.user.data.UserRepository
import com.pipel.mypost.user.domain.User
import com.pipel.mypost.user.mapper.UserMapper
import javax.inject.Inject

interface GetUserUseCase {

    suspend fun execute(userId: Int): User

}

class DefaultGetUserUseCase @Inject constructor(private val userRepository: UserRepository) :
    GetUserUseCase {

    override suspend fun execute(userId: Int): User =
        UserMapper.mapFromDataToDomain(userRepository.getUser(userId = userId))

}
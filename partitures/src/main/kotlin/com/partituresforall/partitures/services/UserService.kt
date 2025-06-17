package com.partituresforall.partitures.services

import com.partituresforall.partitures.models.entities.User
import com.partituresforall.partitures.models.requests.CreateUserRequest
import com.partituresforall.partitures.models.requests.UpdateUserRequest
import com.partituresforall.partitures.models.responses.UserResponse
import com.partituresforall.partitures.repositories.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun createUser(request: CreateUserRequest): UserResponse {
        val user = userRepository.save(
            User(
                name = request.name,
                email = request.email,
                password = passwordEncoder.encode(request.password)
            )
        )
        return user.toResponse()
    }

    fun getUserById(id: Long): UserResponse {
        return userRepository.findById(id).get().toResponse()
    }

    fun updateUser(id: Long, request: UpdateUserRequest): UserResponse {
        val user = userRepository.findById(id).get()
        request.name?.let { user.name = it }
        request.email?.let { user.email = it }
        request.password?.let { user.password = passwordEncoder.encode(it) }
        return userRepository.save(user).toResponse()
    }

    fun deleteUser(id: Long) = userRepository.deleteById(id)

    private fun User.toResponse() = UserResponse(
        id = this.id,
        name = this.name,
        email = this.email,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )
}




/*package com.partituresforall.partitures.services

import com.partituresforall.partitures.models.entities.User
package com.partituresforall.partitures.services

import com.partituresforall.partitures.models.entities.User
import com.partituresforall.partitures.models.requests.CreateUserRequest
import com.partituresforall.partitures.models.requests.UpdateUserRequest
import com.partituresforall.partitures.models.responses.UserResponse
import com.partituresforall.partitures.repositories.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun createUser(request: CreateUserRequest): UserResponse {
        val user = userRepository.save(
            User(
                name = request.name,
                email = request.email,
                password = passwordEncoder.encode(request.password)
            )
        )
        return user.toResponse()
    }

    fun getUserById(id: Long): UserResponse {
        return userRepository.findById(id).get().toResponse()
    }

    fun updateUser(id: Long, request: UpdateUserRequest): UserResponse {
        val user = userRepository.findById(id).get()
        request.name?.let { user.name = it }
        request.email?.let { user.email = it }
        request.password?.let { user.password = passwordEncoder.encode(it) }
        return userRepository.save(user).toResponse()
    }

    fun deleteUser(id: Long) = userRepository.deleteById(id)

    private fun User.toResponse() = UserResponse(
        id = this.id,
        name = this.name,
        email = this.email,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )
}*/
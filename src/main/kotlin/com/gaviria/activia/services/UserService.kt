package com.gaviria.activia.services

import com.gaviria.activia.exceptions.BusinessException
import com.gaviria.activia.models.dto.UserDTO
import com.gaviria.activia.models.entities.User
import com.gaviria.activia.repositories.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
   private val userRepository: UserRepository,
   private val passwordEncoder: PasswordEncoder
) {

   fun getUserByEmail(email: String): User? = userRepository.findByEmail(email)

   fun createUser(userDTO: UserDTO): User {
      if (userRepository.findByEmail(userDTO.email) != null) {
         throw BusinessException("Email already in use")
      }
      val hashedPassword = passwordEncoder.encode(userDTO.password)
      val user = User(email = userDTO.email, passwordHash = hashedPassword, role = userDTO.role)
      return userRepository.save(user)
   }

   fun validateUserPassword(email: String, rawPassword: String): Boolean {
      val user = userRepository.findByEmail(email) ?: return false
      return passwordEncoder.matches(rawPassword, user.passwordHash)
   }
}
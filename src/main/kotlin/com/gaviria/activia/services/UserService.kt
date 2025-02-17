package com.gaviria.activia.services

import com.gaviria.activia.exceptions.BusinessException
import com.gaviria.activia.models.entities.User
import com.gaviria.activia.models.enums.UserRole
import com.gaviria.activia.models.enums.UserStatus
import com.gaviria.activia.repositories.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
   private val userRepository: UserRepository,
   private val passwordEncoder: PasswordEncoder
) {

   fun getUserByEmail(email: String): User? = userRepository.findByEmail(email)

   fun createUser(email: String, role: UserRole, password: String): User {
      if (userRepository.findByEmail(email) != null) {
         throw BusinessException("Email already in use")
      }
      val hashedPassword = passwordEncoder.encode(password)
      val user = User(email = email, passwordHash = hashedPassword, role = role, status = UserStatus.ACTIVE)
      return userRepository.save(user)
   }

   fun getUsers(): List<User> = userRepository.findAll()

   fun getUsersByStatus(status: UserStatus): List<User> = userRepository.findByStatus(status)

   fun inactiveUser(id: Long) {
      val user = userRepository.findById(id).orElseThrow { BusinessException("User not found") }
      user.status = UserStatus.INACTIVE
      userRepository.save(user)
   }

   fun activeUser(id: Long) {
      val user = userRepository.findById(id).orElseThrow { BusinessException("User not found") }
      user.status = UserStatus.ACTIVE
      userRepository.save(user)
   }

   fun updateUser(id: Long, email: String?, role: UserRole?, password: String?, status: UserStatus?) {
      val user = userRepository.findById(id).orElseThrow { BusinessException("User not found") }
      email?.let { user.email = it }
      role?.let { user.role = role }
      password?.let { user.passwordHash = passwordEncoder.encode(it) }
      status?.let { user.status = it }
      userRepository.save(user)
   }

   fun validateUserPassword(email: String, rawPassword: String): Boolean {
      val user = userRepository.findByEmail(email) ?: return false
      if (user.status != UserStatus.ACTIVE) {
         return false
      }
      return passwordEncoder.matches(rawPassword, user.passwordHash)
   }
}
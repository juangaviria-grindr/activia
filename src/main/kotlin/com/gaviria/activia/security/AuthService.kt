package com.gaviria.activia.security

import com.gaviria.activia.services.UserService
import org.springframework.stereotype.Service

@Service
class AuthService(
   private val userService: UserService,
   private val jwtUtil: JwtUtil
) {

   fun login(email: String, password: String): Result<String> {
      return userService.validateUserPassword(email, password).map { user ->
         jwtUtil.generateToken(user.id, user.email, user.role)
      }
   }
}
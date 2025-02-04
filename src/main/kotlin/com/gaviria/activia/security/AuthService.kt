package com.gaviria.activia.security

import com.gaviria.activia.services.UserService
import org.springframework.stereotype.Service

@Service
class AuthService(
   private val userService: UserService,
   private val jwtUtil: JwtUtil
) {

   fun login(email: String, password: String): String? {
      return if (userService.validateUserPassword(email, password)) {
         jwtUtil.generateToken(email)
      } else {
         null
      }
   }
}
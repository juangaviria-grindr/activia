package com.gaviria.activia.controllers

import com.gaviria.activia.models.dto.LoginRequest
import com.gaviria.activia.models.entities.Auth
import com.gaviria.activia.security.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(private val authService: AuthService) {

   @PostMapping("/login")
   fun login(@RequestBody request: LoginRequest): ResponseEntity<Auth?> {
      val token = authService.login(request.email, request.password)
      return if (token != null) {
         ResponseEntity.ok(Auth(token)) // 🔥 Devuelve el token JWT
      } else {
         ResponseEntity.status(401).build() // 🚫 Error de autenticación
      }
   }
}
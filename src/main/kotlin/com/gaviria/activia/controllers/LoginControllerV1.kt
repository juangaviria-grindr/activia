package com.gaviria.activia.controllers

import com.gaviria.activia.exceptions.UnauthorizedException
import com.gaviria.activia.models.dto.LoginRequest
import com.gaviria.activia.models.entities.Auth
import com.gaviria.activia.security.AuthService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth/")
class LoginControllerV1(private val authService: AuthService) {

   @PostMapping("login")
   fun login(@Valid @RequestBody request: LoginRequest): Auth {
      val token = authService.login(request.email, request.password) ?: throw UnauthorizedException("Credenciales inválidas")
      return Auth(token)
   }
}
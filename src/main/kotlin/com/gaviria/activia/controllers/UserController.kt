package com.gaviria.activia.controllers

import com.gaviria.activia.models.dto.UserDTO
import com.gaviria.activia.models.entities.User
import com.gaviria.activia.services.UserService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

   @PostMapping
   fun createUser(@Valid @RequestBody userDTO: UserDTO): User {
      return userService.createUser(userDTO)
   }
}
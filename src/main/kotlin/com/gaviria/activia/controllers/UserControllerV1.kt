package com.gaviria.activia.controllers

import com.gaviria.activia.models.dto.CreateUserRequestV1
import com.gaviria.activia.models.dto.UpdateUserRequestV1
import com.gaviria.activia.models.dto.UserResponseV1
import com.gaviria.activia.models.dto.toUserResponseV1
import com.gaviria.activia.models.entities.User
import com.gaviria.activia.models.enums.UserStatus
import com.gaviria.activia.services.UserService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/users")
class UserControllerV1(private val userService: UserService) {

   @PostMapping
   fun createUser(@Valid @RequestBody request: CreateUserRequestV1): UserResponseV1 {
      return userService.createUser(request.email, request.role, request.password).toUserResponseV1()
   }

   @GetMapping
   fun getUsers(@RequestParam(required = false) status: UserStatus?): List<UserResponseV1> {
      return if (status != null) {
         userService.getUsersByStatus(status).map { it.toUserResponseV1() }
      } else {
         userService.getUsers().map { it.toUserResponseV1() }
      }
   }

   @DeleteMapping("/{id}")
   fun inactiveUser(@PathVariable id: Long) {
      userService.inactiveUser(id)
   }

   @PatchMapping("/{id}/activate")
   fun activeUser(@PathVariable id: Long) {
      userService.activeUser(id)
   }

   @PutMapping("/{id}")
   fun updateUser(@PathVariable id: Long, @RequestBody request: UpdateUserRequestV1) {
      userService.updateUser(id, request.email, request.role, request.password, request.status)
   }
}
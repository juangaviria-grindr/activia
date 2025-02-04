package com.gaviria.activia.models.dto

import com.gaviria.activia.models.enums.UserRole
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class UserDTO(
   @field:Email(message = "Invalid email format")
   @field:NotBlank(message = "Email is required")
   val email: String,
   @field:NotBlank(message = "Password is required")
   @field:Size(min = 6, message = "Password must be at least 6 characters long")
   val password: String,
   val role: UserRole
)
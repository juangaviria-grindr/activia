package com.gaviria.activia.models.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.time.LocalDate

data class CreateEmployeeRequestV1 (
   @field:NotBlank(message = "First name is required")
   @field:Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
   val firstName: String,

   @field:NotBlank(message = "Last name is required")
   @field:Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
   val lastName: String,

   @field:NotBlank(message = "Email is required")
   @field:Email(message = "Email is invalid")
   val email: String,

   val hireDate: LocalDate?,

   @field:NotBlank(message = "Password is required")
   @field:Size(min = 6, message = "Password must be at least 6 characters long")
   val password: String
)
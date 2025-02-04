package com.gaviria.activia.models.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class EmployeeDTO(
   @field:NotBlank(message = "First name is required")
   @field:Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
   val firstName: String,
   @field:NotBlank(message = "Last name is required")
   @field:Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
   val lastName: String,
)
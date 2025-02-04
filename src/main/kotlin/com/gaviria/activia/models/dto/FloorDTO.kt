package com.gaviria.activia.models.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class FloorDTO(
   @field:NotBlank(message = "Name is required")
   @field:Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
   val name: String,

   val description: String? = null
)
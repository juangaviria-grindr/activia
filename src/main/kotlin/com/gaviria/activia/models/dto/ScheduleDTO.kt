package com.gaviria.activia.models.dto

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min

data class ScheduleDTO(
   @field:Min(value = 1, message = "Month must be between 1 and 12")
   @field:Max(value = 12, message = "Month must be between 1 and 12")
   val month: Int,
   @field:Min(value = 2024, message = "Year must be at least 2024")
   val year: Int
)
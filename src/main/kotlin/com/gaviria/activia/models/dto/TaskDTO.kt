package com.gaviria.activia.models.dto

import com.gaviria.activia.models.enums.Frequency
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.DayOfWeek
import java.time.LocalTime

data class TaskDTO(
   @field:NotBlank(message = "Task name is required")
   @field:Size(min = 2, max = 200, message = "Task name must be between 2 and 100 characters")
   val name: String,
   @field:Size(max = 600, message = "Description must be less than 500 characters")
   val description: String?,
   @field:NotNull(message = "Start Time is required")
   val startTime: LocalTime,
   @field:NotNull(message = "Start Time is required")
   val endTime: LocalTime,
   @field:NotNull(message = "Frequency is required")
   val frequency: Frequency,
   @field:NotNull(message = "Floor ID is required")
   val floorId: Long,
   val weeklyDays: List<DayOfWeek>?
)
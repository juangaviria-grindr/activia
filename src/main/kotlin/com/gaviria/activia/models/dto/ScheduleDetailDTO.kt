package com.gaviria.activia.models.dto

import com.gaviria.activia.models.enums.Frequency
import jakarta.validation.constraints.NotNull
import java.time.DayOfWeek
import java.time.LocalTime

data class ScheduleDetailDTO(
   @field:NotNull(message = "Schedule ID is required")
   val scheduleId: Long,
   @field:NotNull(message = "Task ID is required")
   val taskId: Long,
   @field:NotNull(message = "Employee ID is required")
   val employeeId: Long,
   @field:NotNull(message = "Start time is required")
   val startTime: LocalTime,
   @field:NotNull(message = "End time is required")
   val endTime: LocalTime,
   @field:NotNull(message = "Frequency is required")
   val frequency: Frequency,
   val weeklyDays: List<DayOfWeek>? = null,
   val specificDays: List<Int>? = null
)
package com.gaviria.activia.models.dto

import com.gaviria.activia.models.enums.TaskStatus
import jakarta.validation.constraints.NotNull
import java.time.LocalDate

data class DailyScheduleDTO(
   @field:NotNull(message = "Schedule detail ID is required")
   val scheduleDetailId: Long,
   @field:NotNull(message = "Date is required")
   val date: LocalDate,
   val status: TaskStatus = TaskStatus.PENDING,
   val comments: String? = null
)
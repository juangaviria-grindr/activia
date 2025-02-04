package com.gaviria.activia.models.dto

import jakarta.validation.constraints.NotNull

data class FloorScheduleDTO(
   @field:NotNull(message = "Schedule ID is required")
   val scheduleId: Long,

   @field:NotNull(message = "Floor ID is required")
   val floorId: Long,

   @field:NotNull(message = "Employee ID is required")
   val employeeId: Long
)
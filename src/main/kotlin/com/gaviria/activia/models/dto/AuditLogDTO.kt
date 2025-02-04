package com.gaviria.activia.models.dto

import com.gaviria.activia.models.enums.AuditStatus
import jakarta.validation.constraints.NotNull

data class AuditLogDTO(
   @field:NotNull(message = "Daily schedule ID is required")
   val dailyScheduleId: Long,
   @field:NotNull(message = "Auditor ID is required")
   val auditorId: Long,
   @field:NotNull(message = "Audit status is required")
   val status: AuditStatus,
   val comments: String? = null
)
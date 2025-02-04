package com.gaviria.activia.controllers

import com.gaviria.activia.models.dto.AuditLogDTO
import com.gaviria.activia.models.entities.AuditLog
import com.gaviria.activia.models.enums.AuditStatus
import com.gaviria.activia.services.AuditLogService
import jakarta.validation.Valid
import java.time.Instant
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/audit-logs")
class AuditLogController(private val auditLogService: AuditLogService) {

   @GetMapping("/daily-schedule/{dailyScheduleId}")
   fun getAuditsByDailySchedule(@PathVariable dailyScheduleId: Long): List<AuditLog> =
      auditLogService.getAuditsByDailySchedule(dailyScheduleId)

   @PostMapping
   fun createAuditLog(@Valid @RequestBody auditLog: AuditLogDTO): AuditLog = auditLogService.createAuditLog(auditLog)

   @GetMapping("/status/{status}")
   fun getAuditsByStatus(@PathVariable status: AuditStatus): List<AuditLog> =
      auditLogService.getAuditsByStatus(status)

   @GetMapping("/between")
   fun getAuditsBetweenDates(
      @RequestParam start: Instant,
      @RequestParam end: Instant
   ): List<AuditLog> = auditLogService.getAuditsBetweenDates(start, end)
}
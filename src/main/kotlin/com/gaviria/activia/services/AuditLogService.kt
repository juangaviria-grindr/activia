package com.gaviria.activia.services

import com.gaviria.activia.exceptions.ResourceNotFoundException
import com.gaviria.activia.models.dto.AuditLogDTO
import com.gaviria.activia.models.entities.AuditLog
import com.gaviria.activia.models.enums.AuditStatus
import com.gaviria.activia.repositories.AuditLogRepository
import com.gaviria.activia.repositories.DailyScheduleRepository
import com.gaviria.activia.repositories.UserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class AuditLogService(
   private val auditLogRepository: AuditLogRepository,
   private val dailyScheduleRepository: DailyScheduleRepository,
   private val userRepository: UserRepository
) {

   fun getAuditsByDailySchedule(dailyScheduleId: Long): List<AuditLog> =
      auditLogRepository.findByDailyScheduleId(dailyScheduleId)

   @Transactional
   fun createAuditLog(dto: AuditLogDTO): AuditLog {
      val dailySchedule = dailyScheduleRepository.findById(dto.dailyScheduleId).orElseThrow { ResourceNotFoundException("Daily schedule not found") }
      val auditor = userRepository.findById(dto.auditorId).orElseThrow { ResourceNotFoundException("Auditor not found") }
      val auditLog = AuditLog(
         dailySchedule = dailySchedule,
         auditor = auditor,
         status = dto.status,
         comments = dto.comments,
         auditedAt = Instant.now()
      )
      return auditLogRepository.save(auditLog)
   }

   fun getAuditsByStatus(status: AuditStatus): List<AuditLog> = auditLogRepository.findByStatus(status)

   fun getAuditsBetweenDates(start: Instant, end: Instant): List<AuditLog> =
      auditLogRepository.findByAuditedAtBetween(start, end)
}
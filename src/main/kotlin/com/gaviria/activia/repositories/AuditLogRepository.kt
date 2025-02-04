package com.gaviria.activia.repositories

import com.gaviria.activia.models.entities.AuditLog
import com.gaviria.activia.models.enums.AuditStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.Instant

@Repository
interface AuditLogRepository : JpaRepository<AuditLog, Long> {
   fun findByDailyScheduleId(dailyScheduleId: Long): List<AuditLog>
   fun findByAuditorId(auditorId: Long): List<AuditLog>
   fun findByStatus(status: AuditStatus): List<AuditLog>
   fun findByAuditedAtBetween(start: Instant, end: Instant): List<AuditLog>
}
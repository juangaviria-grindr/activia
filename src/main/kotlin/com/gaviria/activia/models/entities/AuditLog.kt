package com.gaviria.activia.models.entities

import com.gaviria.activia.models.enums.AuditStatus
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.Instant

@Entity
@Table(name = "audit_logs")
data class AuditLog (
   @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
   val id: Long? = null,

   @ManyToOne
   @JoinColumn(name = "daily_schedule_id", nullable = false)
   val dailySchedule: DailySchedule,

   @ManyToOne
   @JoinColumn(name = "auditor_id", nullable = false)
   val auditor: User,

   @Enumerated(EnumType.STRING)
   val status: AuditStatus,

   @Column
   val comments: String? = null,

   @Column(nullable = false)
   val auditedAt: Instant = Instant.now()
)
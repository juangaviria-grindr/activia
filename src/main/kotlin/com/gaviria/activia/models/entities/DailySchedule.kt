package com.gaviria.activia.models.entities

import com.gaviria.activia.models.enums.TaskStatus
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
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "daily_schedules")
data class DailySchedule(
   @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
   val id: Long? = null,

   @ManyToOne
   @JoinColumn(name = "task_id", nullable = false)
   val task: Task,

   @ManyToOne
   @JoinColumn(name = "employee_id", nullable = false)
   val employee: Employee,

   @ManyToOne
   @JoinColumn(name = "schedule_id", nullable = false)
   val schedule: Schedule,

   @Column(nullable = false)
   val startTime: Instant,

   @Column(nullable = false)
   val endTime: Instant,

   @Enumerated(EnumType.STRING)
   val status: TaskStatus = TaskStatus.PENDING,

   @Column
   val comments: String? = null,

)

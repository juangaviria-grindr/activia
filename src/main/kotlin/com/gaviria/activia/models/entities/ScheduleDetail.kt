package com.gaviria.activia.models.entities

import com.gaviria.activia.models.enums.Frequency
import jakarta.persistence.CollectionTable
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.DayOfWeek
import java.time.LocalTime

@Entity
@Table(name = "schedule_details")
data class ScheduleDetail (
   @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
   val id: Long? = null,

   @ManyToOne
   @JoinColumn(name = "schedule_id", nullable = false)
   val schedule: Schedule,

   @ManyToOne
   @JoinColumn(name = "task_id", nullable = false)
   val task: Task,

   @ManyToOne
   @JoinColumn(name = "employee_id", nullable = false)
   val employee: Employee,

   @Column(nullable = false)
   val startTime: LocalTime,

   @Column(nullable = false)
   val endTime: LocalTime,

   @Enumerated(EnumType.STRING)
   val frequency: Frequency,

   @ElementCollection
   @CollectionTable(name = "schedule_detail_days", joinColumns = [JoinColumn(name = "schedule_detail_id")])
   @Column(name = "day")
   val specificDays: List<Int>? = null,

   @ElementCollection
   @CollectionTable(name = "schedule_detail_weekly_days", joinColumns = [JoinColumn(name = "schedule_detail_id")])
   val weeklyDays: List<DayOfWeek>? = null

)
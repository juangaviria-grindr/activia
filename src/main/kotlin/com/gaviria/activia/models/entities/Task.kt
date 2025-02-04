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
@Table(name = "tasks")
data class Task (
   @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
   val id: Long? = null,

   @Column(nullable = false)
   val name: String,

   @Column
   var description: String? = null,

   @Column(nullable = false)
   val startTime: LocalTime,

   @Column(nullable = false)
   val endTime: LocalTime,

   @Enumerated(EnumType.STRING)
   val frequency: Frequency,

   @ElementCollection
   @CollectionTable(name = "task_weekly_days", joinColumns = [JoinColumn(name = "task_id")])
   val weeklyDays: List<DayOfWeek>? = null,

   @ManyToOne
   @JoinColumn(name = "floor_id", nullable = false)
   val floor: Floor
)
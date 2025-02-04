package com.gaviria.activia.models.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "floor_schedules")
data class FloorSchedule(
   @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
   val id: Long? = null,

   @ManyToOne
   @JoinColumn(name = "schedule_id", nullable = false)
   val schedule: Schedule,

   @ManyToOne
   @JoinColumn(name = "floor_id", nullable = false)
   val floor: Floor,

   @ManyToOne
   @JoinColumn(name = "employee_id", nullable = false)
   val responsible: Employee
)
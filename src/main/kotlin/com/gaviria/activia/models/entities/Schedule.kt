package com.gaviria.activia.models.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.Instant

@Entity
@Table(name = "schedules")
data class Schedule (
   @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
   val id: Long? = null,

   @Column(nullable = false)
   val month: Int,

   @Column(nullable = false)
   val year: Int,

   @Column(nullable = false)
   val createdAt: Instant = Instant.now(),
)
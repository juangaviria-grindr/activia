package com.gaviria.activia.models.entities

import com.gaviria.activia.models.enums.EmployeeStatus
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "employees")
data class Employee (
   @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
   val id: Long = 0,

   @Column(nullable = false)
   var firstName: String,

   @Column(nullable = false)
   var lastName: String,

   @Enumerated(EnumType.STRING)
   var status: EmployeeStatus,

   @Column(nullable = true)
   var hireDate: LocalDate?,

   @OneToOne
   @JoinColumn(name = "user_id", unique = true, nullable = false)
   var user: User
)
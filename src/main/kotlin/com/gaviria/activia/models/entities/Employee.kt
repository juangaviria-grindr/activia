package com.gaviria.activia.models.entities

import com.gaviria.activia.models.enums.EmployeeStatus
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "employees")
data class Employee (
   @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
   val id: Long? = null,

   @Column(nullable = false)
   var firstName: String,

   @Column(nullable = false)
   var lastName: String,

   @Enumerated(EnumType.STRING)
   val status: EmployeeStatus = EmployeeStatus.ACTIVE
)
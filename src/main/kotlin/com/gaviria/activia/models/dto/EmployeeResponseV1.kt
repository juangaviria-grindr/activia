package com.gaviria.activia.models.dto

import com.gaviria.activia.models.entities.Employee
import com.gaviria.activia.models.enums.EmployeeStatus
import java.time.LocalDate

data class EmployeeResponseV1 (
   val id: Long,
   val firstName: String,
   val lastName: String,
   val email: String,
   val hireDate: LocalDate?,
   val status: EmployeeStatus,
)

fun Employee.toEmployeeResponseV1() = EmployeeResponseV1(id, firstName, lastName, user.email, hireDate, status)
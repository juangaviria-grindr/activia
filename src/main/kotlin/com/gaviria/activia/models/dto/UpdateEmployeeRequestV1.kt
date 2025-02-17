package com.gaviria.activia.models.dto

import com.gaviria.activia.models.enums.EmployeeStatus
import jakarta.validation.constraints.Size
import java.time.LocalDate

data class UpdateEmployeeRequestV1 (
   @field:Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
   val firstName: String?,

   @field:Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
   val lastName: String?,

   val status: EmployeeStatus?,

   val hireDate: LocalDate?
)
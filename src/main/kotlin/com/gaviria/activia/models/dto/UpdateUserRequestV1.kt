package com.gaviria.activia.models.dto

import com.gaviria.activia.models.enums.UserRole
import com.gaviria.activia.models.enums.UserStatus
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Size

data class UpdateUserRequestV1 (
   @field:Email(message = "Invalid email format")
   val email: String?,

   @field:Size(min = 6, message = "Password must be at least 6 characters long")
   val password: String?,

   val role: UserRole?,

   val status: UserStatus?
)
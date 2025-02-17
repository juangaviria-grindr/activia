package com.gaviria.activia.models.dto

import com.gaviria.activia.models.entities.User
import com.gaviria.activia.models.enums.UserRole
import com.gaviria.activia.models.enums.UserStatus

data class UserResponseV1 (
   val id: Long,
   val email: String,
   val role: UserRole,
   val status: UserStatus
)

fun User.toUserResponseV1() = UserResponseV1(id, email, role, status)
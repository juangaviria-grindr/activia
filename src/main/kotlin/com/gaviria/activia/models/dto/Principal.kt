package com.gaviria.activia.models.dto

import com.gaviria.activia.models.enums.UserRole

data class Principal(
   val id: Long,
   val email: String,
   val role: UserRole
)
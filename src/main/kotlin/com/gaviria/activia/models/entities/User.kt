package com.gaviria.activia.models.entities

import com.gaviria.activia.models.enums.UserRole
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "users")
data class User(
   @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
   val id: Long? = null,

   @Column(nullable = false, unique = true)
   val email: String,

   @Column(nullable = false)
   val passwordHash: String,

   @Enumerated(EnumType.STRING)
   val role: UserRole
)

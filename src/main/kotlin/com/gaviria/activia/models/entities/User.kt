package com.gaviria.activia.models.entities

import com.gaviria.activia.models.enums.UserRole
import com.gaviria.activia.models.enums.UserStatus
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
   val id: Long = 0,

   @Column(nullable = false, unique = true)
   var email: String,

   @Column(nullable = false)
   var passwordHash: String,

   @Enumerated(EnumType.STRING)
   var role: UserRole,

   @Enumerated(EnumType.STRING)
   var status: UserStatus
)

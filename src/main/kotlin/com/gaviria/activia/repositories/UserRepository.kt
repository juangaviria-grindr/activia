package com.gaviria.activia.repositories

import com.gaviria.activia.models.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
   fun findByEmail(email: String): User?
}
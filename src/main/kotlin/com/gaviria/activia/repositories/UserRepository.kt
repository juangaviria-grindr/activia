package com.gaviria.activia.repositories

import com.gaviria.activia.models.entities.User
import com.gaviria.activia.models.enums.UserStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
   fun findByEmail(email: String): User?
   fun findByStatus(status: UserStatus): List<User>
}
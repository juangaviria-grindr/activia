package com.gaviria.activia.repositories

import com.gaviria.activia.models.entities.Employee
import com.gaviria.activia.models.enums.EmployeeStatus
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface EmployeeRepository : JpaRepository<Employee, Long> {
   fun findByStatus(status: EmployeeStatus): List<Employee>

   @Modifying
   @Transactional
   @Query("UPDATE Employee e SET e.status = :status WHERE e.id = :id")
   fun updateEmployeeStatus(id: Long, status: EmployeeStatus): Int // Retorna el número de filas afectadas
}
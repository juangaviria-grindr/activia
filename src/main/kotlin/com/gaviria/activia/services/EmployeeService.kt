package com.gaviria.activia.services

import com.gaviria.activia.models.dto.EmployeeDTO
import com.gaviria.activia.models.entities.Employee
import com.gaviria.activia.models.enums.EmployeeStatus
import com.gaviria.activia.repositories.EmployeeRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class EmployeeService(private val employeeRepository: EmployeeRepository) {

   fun getAllEmployees(): List<Employee> = employeeRepository.findAll()

   fun getEmployeeById(id: Long): Employee? = employeeRepository.findById(id).orElse(null)

   fun getActiveEmployees(): List<Employee> = employeeRepository.findByStatus(EmployeeStatus.ACTIVE)

   fun createEmployee(employeeDTO: EmployeeDTO): Employee {
      val employee = Employee(firstName = employeeDTO.firstName, lastName = employeeDTO.lastName)
      return employeeRepository.save(employee)
   }

   fun updateEmployee(id: Long, employeeDTO: EmployeeDTO): Employee {
      val employee = getEmployeeById(id) ?: throw IllegalArgumentException("Employee not found")
      employee.firstName = employeeDTO.firstName
      employee.lastName = employeeDTO.lastName
      return employeeRepository.save(employee)
   }

   @Transactional
   fun deactivateEmployee(id: Long): Boolean {
      val rowsUpdated = employeeRepository.updateEmployeeStatus(id, EmployeeStatus.INACTIVE)
      return rowsUpdated > 0 // Retorna `true` si al menos una fila fue modificada
   }
}
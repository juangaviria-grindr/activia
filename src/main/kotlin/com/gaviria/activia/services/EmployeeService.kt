package com.gaviria.activia.services

import com.gaviria.activia.models.entities.Employee
import com.gaviria.activia.models.enums.EmployeeStatus
import com.gaviria.activia.models.enums.UserRole
import com.gaviria.activia.repositories.EmployeeRepository
import jakarta.transaction.Transactional
import java.time.LocalDate
import org.springframework.stereotype.Service

@Service
class EmployeeService(
   private val userService: UserService,
   private val employeeRepository: EmployeeRepository
) {

   fun getAllEmployees(): List<Employee> = employeeRepository.findAll()

   fun getEmployeeById(id: Long): Employee? = employeeRepository.findById(id).orElse(null)

   fun getEmployeesByStatus(status: EmployeeStatus): List<Employee> = employeeRepository.findByStatus(status)

   fun createEmployee(
      firstName: String,
      lastName: String,
      email: String,
      hireDate: LocalDate?,
      password: String
   ): Employee {
      val user = userService.createUser(email, UserRole.EMPLOYEE, password)
      val employee = Employee(
         firstName = firstName,
         lastName = lastName,
         status = EmployeeStatus.ACTIVE,
         hireDate = hireDate,
         user = user
      )
      return employeeRepository.save(employee)
   }

   fun updateEmployee(
      id: Long,
      firstName: String?,
      lastName: String?,
      status: EmployeeStatus?,
      hireDate: LocalDate?
   ): Employee {
      val employee = getEmployeeById(id) ?: throw IllegalArgumentException("Employee not found")
      firstName?.let { employee.firstName = it }
      lastName?.let { employee.lastName = it }
      status?.let { employee.status = it }
      hireDate?.let { employee.hireDate = it }

      return employeeRepository.save(employee)
   }

   @Transactional
   fun deactivateEmployee(id: Long) {
      val employee = getEmployeeById(id) ?: throw IllegalArgumentException("Employee not found")
      employee.status = EmployeeStatus.INACTIVE
      employeeRepository.save(employee)
      userService.inactiveUser(employee.user.id)
   }

   @Transactional
   fun activateEmployee(id: Long) {
      val employee = getEmployeeById(id) ?: throw IllegalArgumentException("Employee not found")
      employee.status = EmployeeStatus.ACTIVE
      employeeRepository.save(employee)
      userService.activeUser(employee.user.id)
   }
}
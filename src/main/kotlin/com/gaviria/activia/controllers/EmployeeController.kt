package com.gaviria.activia.controllers

import com.gaviria.activia.models.dto.EmployeeDTO
import com.gaviria.activia.models.entities.Employee
import com.gaviria.activia.services.EmployeeService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/employees")
class EmployeeController(private val employeeService: EmployeeService) {

   @GetMapping
   fun getAllEmployees(): List<Employee> = employeeService.getAllEmployees()

   @GetMapping("/{id}")
   fun getEmployeeById(@PathVariable id: Long): ResponseEntity<Employee> {
      return employeeService.getEmployeeById(id)?.let {
         ResponseEntity.ok(it)
      } ?: ResponseEntity.notFound().build()
   }

   @GetMapping("/active")
   fun getActiveEmployees(): List<Employee> = employeeService.getActiveEmployees()

   @PostMapping
   fun createEmployee(@Valid @RequestBody employeeDTO: EmployeeDTO): Employee {
      return employeeService.createEmployee(employeeDTO)
   }

   @PutMapping("/{id}")
   fun updateEmployee(@PathVariable id: Long, @Valid @RequestBody employeeDTO: EmployeeDTO): ResponseEntity<Employee> {
      val employee = employeeService.updateEmployee(id, employeeDTO)
      return ResponseEntity.ok(employee)
   }

   @DeleteMapping("/{id}")
   fun deactivateEmployee(@PathVariable id: Long): ResponseEntity<Unit> {
      return if (employeeService.deactivateEmployee(id)) {
         ResponseEntity.ok().build()
      } else {
         ResponseEntity.badRequest().build()
      }
   }
}
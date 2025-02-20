package com.gaviria.activia.controllers

import com.gaviria.activia.models.dto.CreateEmployeeRequestV1
import com.gaviria.activia.models.dto.EmployeeResponseV1
import com.gaviria.activia.models.dto.UpdateEmployeeRequestV1
import com.gaviria.activia.models.dto.toEmployeeResponseV1
import com.gaviria.activia.models.enums.EmployeeStatus
import com.gaviria.activia.services.EmployeeService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/employees")
class EmployeeController(private val employeeService: EmployeeService) {

   @PreAuthorize("hasAnyRole('ADMIN', 'SUPERVISOR')")
   @GetMapping
   fun getAllEmployees(@RequestParam(required = false) status: EmployeeStatus?): List<EmployeeResponseV1> {
      return if (status != null) {
         employeeService.getEmployeesByStatus(status).map { it.toEmployeeResponseV1() }
      } else {
         employeeService.getAllEmployees().map { it.toEmployeeResponseV1() }
      }
   }

   @PreAuthorize("hasAnyRole('ADMIN', 'SUPERVISOR')")
   @GetMapping("/{id}")
   fun getEmployeeById(@PathVariable id: Long): ResponseEntity<EmployeeResponseV1> {
      return employeeService.getEmployeeById(id)?.let {
         ResponseEntity.ok(it.toEmployeeResponseV1())
      } ?: ResponseEntity.notFound().build()
   }

   @PreAuthorize("hasAnyRole('ADMIN', 'SUPERVISOR')")
   @PostMapping
   fun createEmployee(@Valid @RequestBody request: CreateEmployeeRequestV1): EmployeeResponseV1 {
      return employeeService.createEmployee(
         request.firstName, request.lastName, request.email, request.hireDate, request.password
      ).toEmployeeResponseV1()
   }

   @PreAuthorize("hasAnyRole('ADMIN', 'SUPERVISOR')")
   @PutMapping("/{id}")
   fun updateEmployee(
      @PathVariable id: Long,
      @Valid @RequestBody request: UpdateEmployeeRequestV1
   ): ResponseEntity<EmployeeResponseV1> {
      val employee = employeeService.updateEmployee(id, request.firstName, request.lastName, request.status, request.hireDate)
      return ResponseEntity.ok(employee.toEmployeeResponseV1())
   }

   @PreAuthorize("hasAnyRole('ADMIN', 'SUPERVISOR')")
   @DeleteMapping("/{id}")
   fun deactivateEmployee(@PathVariable id: Long): ResponseEntity<Unit> {
      return employeeService.deactivateEmployee(id).let {
         ResponseEntity.ok().build()
      }
   }

   @PreAuthorize("hasAnyRole('ADMIN', 'SUPERVISOR')")
   @PatchMapping("/{id}/activate")
   fun activateEmployee(@PathVariable id: Long): ResponseEntity<Unit> {
      return employeeService.activateEmployee(id).let {
         ResponseEntity.ok().build()
      }
   }
}
package com.gaviria.activia.controllers

import com.gaviria.activia.models.dto.FloorScheduleDTO
import com.gaviria.activia.models.entities.FloorSchedule
import com.gaviria.activia.services.FloorScheduleService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/floor-schedules")
class FloorScheduleController(private val floorScheduleService: FloorScheduleService) {

   @PostMapping
   fun createFloorSchedule(@Valid @RequestBody dto: FloorScheduleDTO): ResponseEntity<FloorSchedule> {
      return try {
         val savedFloorSchedule = floorScheduleService.createFloorSchedule(dto)
         ResponseEntity.ok(savedFloorSchedule)
      } catch (ex: IllegalArgumentException) {
         ResponseEntity.badRequest().body(null)
      }
   }

   @PostMapping("/bulk")
   fun createBulkFloorSchedules(@Valid @RequestBody dtos: List<FloorScheduleDTO>): ResponseEntity<List<FloorSchedule>> {
      return try {
         val savedFloorSchedules = floorScheduleService.createBulkFloorSchedules(dtos)
         ResponseEntity.ok(savedFloorSchedules)
      } catch (ex: IllegalArgumentException) {
         ResponseEntity.badRequest().body(null)
      }
   }
}
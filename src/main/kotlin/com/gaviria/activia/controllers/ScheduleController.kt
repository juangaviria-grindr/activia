package com.gaviria.activia.controllers

import com.gaviria.activia.models.dto.ScheduleDTO
import com.gaviria.activia.models.entities.Schedule
import com.gaviria.activia.services.ScheduleService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/schedules")
class ScheduleController(private val scheduleService: ScheduleService) {

   @GetMapping("/{month}/{year}")
   fun getSchedule(@PathVariable month: Int, @PathVariable year: Int): ResponseEntity<Schedule> {
      val schedule = scheduleService.getSchedule(month, year)
      return if (schedule != null) ResponseEntity.ok(schedule) else ResponseEntity.notFound().build()
   }

   @GetMapping
   fun getAllSchedules(): List<Schedule> {
      return scheduleService.getAllSchedules()
   }

   @PostMapping
   fun createSchedule(@Valid @RequestBody scheduleDTO: ScheduleDTO): Schedule {
      return scheduleService.createSchedule(scheduleDTO)
   }
}
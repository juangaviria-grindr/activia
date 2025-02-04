package com.gaviria.activia.controllers

import com.gaviria.activia.models.dto.DailyScheduleDTO
import com.gaviria.activia.models.entities.DailySchedule
import com.gaviria.activia.models.entities.ScheduleDetail
import com.gaviria.activia.services.DailyScheduleService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/daily-schedules")
class DailyScheduleController(private val dailyScheduleService: DailyScheduleService) {

//   @GetMapping("/date/{date}")
//   fun getSchedulesByDate(@PathVariable date: LocalDate): List<DailySchedule> =
//      dailyScheduleService.getSchedulesByDate(date)

   /*@PostMapping
   fun createDailySchedule(@Valid @RequestBody dailyScheduleDTO: DailyScheduleDTO): DailySchedule {
      return dailyScheduleService.createDailySchedule(dailyScheduleDTO)
   }*/
}
package com.gaviria.activia.controllers

import com.gaviria.activia.models.dto.ScheduleDetailDTO
import com.gaviria.activia.models.entities.ScheduleDetail
import com.gaviria.activia.services.ScheduleDetailService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/schedule-details")
class ScheduleDetailController(private val scheduleDetailService: ScheduleDetailService) {

   @GetMapping("/schedule/{scheduleId}")
   fun getDetailsBySchedule(@PathVariable scheduleId: Long): List<ScheduleDetail> =
      scheduleDetailService.getDetailsBySchedule(scheduleId)

   @PostMapping
   fun createScheduleDetail(@Valid @RequestBody scheduleDetailDTO: ScheduleDetailDTO): ScheduleDetail {
      return scheduleDetailService.createScheduleDetail(scheduleDetailDTO)
   }
}
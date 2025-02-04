package com.gaviria.activia.services

import com.gaviria.activia.models.dto.ScheduleDTO
import com.gaviria.activia.models.entities.Schedule
import com.gaviria.activia.repositories.ScheduleRepository
import org.springframework.stereotype.Service

@Service
class ScheduleService(private val scheduleRepository: ScheduleRepository) {

   fun getAllSchedules(): List<Schedule> = scheduleRepository.findAll()

   fun getSchedule(month: Int, year: Int): Schedule? = scheduleRepository.findByMonthAndYear(month, year)

   fun createSchedule(scheduleDTO: ScheduleDTO): Schedule {
      val schedule = Schedule(month = scheduleDTO.month, year = scheduleDTO.year)
      return scheduleRepository.save(schedule)
   }
}
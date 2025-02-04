package com.gaviria.activia.services

import com.gaviria.activia.exceptions.ResourceNotFoundException
import com.gaviria.activia.models.dto.FloorScheduleDTO
import com.gaviria.activia.models.entities.FloorSchedule
import com.gaviria.activia.repositories.FloorRepository
import com.gaviria.activia.repositories.FloorScheduleRepository
import com.gaviria.activia.repositories.ScheduleRepository
import com.gaviria.activia.repositories.EmployeeRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FloorScheduleService(
   private val floorScheduleRepository: FloorScheduleRepository,
   private val scheduleRepository: ScheduleRepository,
   private val floorRepository: FloorRepository,
   private val employeeRepository: EmployeeRepository,
   private val dailyScheduleService: DailyScheduleService
) {
   @Transactional
   fun createFloorSchedule(dto: FloorScheduleDTO): FloorSchedule {
      val schedule = scheduleRepository.findById(dto.scheduleId).orElseThrow { ResourceNotFoundException("Schedule not found") }
      val floor = floorRepository.findById(dto.floorId).orElseThrow { ResourceNotFoundException("Floor not found") }
      val employee = employeeRepository.findById(dto.employeeId).orElseThrow { ResourceNotFoundException("Employee not found") }

      val floorSchedule = FloorSchedule(schedule = schedule, floor = floor, responsible = employee)
      return floorScheduleRepository.save(floorSchedule)
   }

   @Transactional
   fun createBulkFloorSchedules(dtos: List<FloorScheduleDTO>): List<FloorSchedule> {
      val floorSchedules = dtos.map { dto ->
         val schedule =
            scheduleRepository.findById(dto.scheduleId).orElseThrow { ResourceNotFoundException("Schedule not found") }
         val floor = floorRepository.findById(dto.floorId).orElseThrow { ResourceNotFoundException("Floor not found") }
         val employee =
            employeeRepository.findById(dto.employeeId).orElseThrow { ResourceNotFoundException("Employee not found") }
         FloorSchedule(schedule = schedule, floor = floor, responsible = employee)
      }
      return floorScheduleRepository.saveAll(floorSchedules).map {
         dailyScheduleService.createDailyScheduleForFloorSchedule(it)
         it
      }
   }
}
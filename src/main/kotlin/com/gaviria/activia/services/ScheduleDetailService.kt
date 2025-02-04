package com.gaviria.activia.services

import com.gaviria.activia.exceptions.ResourceNotFoundException
import com.gaviria.activia.models.dto.ScheduleDetailDTO
import com.gaviria.activia.models.entities.ScheduleDetail
import com.gaviria.activia.repositories.EmployeeRepository
import com.gaviria.activia.repositories.ScheduleDetailRepository
import com.gaviria.activia.repositories.ScheduleRepository
import com.gaviria.activia.repositories.TaskRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class ScheduleDetailService(
   private val scheduleDetailRepository: ScheduleDetailRepository,
   private val scheduleRepository: ScheduleRepository,
   private val taskRepository: TaskRepository,
   private val employeeRepository: EmployeeRepository
) {

   fun getDetailsBySchedule(scheduleId: Long): List<ScheduleDetail> = scheduleDetailRepository.findByScheduleId(scheduleId)

   @Transactional
   fun createScheduleDetail(dto: ScheduleDetailDTO): ScheduleDetail {
      val schedule = scheduleRepository.findById(dto.scheduleId).orElseThrow { ResourceNotFoundException("Schedule not found") }
      val task = taskRepository.findById(dto.taskId).orElseThrow { ResourceNotFoundException("Task not found") }
      val employee = employeeRepository.findById(dto.employeeId).orElseThrow { ResourceNotFoundException("Employee not found") }

      val scheduleDetail = ScheduleDetail(
         schedule = schedule,
         task = task,
         employee = employee,
         startTime = dto.startTime,
         endTime = dto.endTime,
         frequency = dto.frequency,
         specificDays = dto.specificDays
      )
      return scheduleDetailRepository.save(scheduleDetail)
   }
}
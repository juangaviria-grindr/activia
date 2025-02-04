package com.gaviria.activia.services

import com.gaviria.activia.models.entities.DailySchedule
import com.gaviria.activia.models.entities.FloorSchedule
import com.gaviria.activia.models.entities.Task
import com.gaviria.activia.models.enums.Frequency
import com.gaviria.activia.models.enums.TaskStatus
import com.gaviria.activia.repositories.DailyScheduleRepository
import com.gaviria.activia.repositories.TaskRepository
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.ZoneId
import org.springframework.stereotype.Service

@Service
class DailyScheduleService(
   private val dailyScheduleRepository: DailyScheduleRepository,
   private val taskRepository: TaskRepository
) {

   fun createDailyScheduleForFloorSchedule(floorSchedule: FloorSchedule): List<DailySchedule> {
      val tasks = taskRepository.findByFloorId(floorSchedule.floor.id!!)
      val dailySchedules = mutableListOf<DailySchedule>()
      tasks.forEach { task ->
         dailySchedules.addAll(createDailySchedulesForTask(floorSchedule, task))
      }
      return dailySchedules
   }

   private fun createDailySchedulesForTask(floorSchedule: FloorSchedule, task: Task): List<DailySchedule> {
      val month = floorSchedule.schedule.month
      val year = floorSchedule.schedule.year
      val firstDayOfMonth = LocalDate.of(year, month, 1)
      val lastDayOfMonth = firstDayOfMonth.withDayOfMonth(firstDayOfMonth.lengthOfMonth())

      val dailySchedules = mutableListOf<DailySchedule>()
      var currentDate = firstDayOfMonth

      while (!currentDate.isAfter(lastDayOfMonth)) {
         if (shouldCreateScheduleForDate(task, currentDate)) {
            dailySchedules.add(createDailySchedule(floorSchedule, task, currentDate))
         }
         currentDate = currentDate.plusDays(1)
      }

      return dailySchedules
   }

   private fun shouldCreateScheduleForDate(task: Task, date: LocalDate): Boolean {
      return when (task.frequency) {
         Frequency.DAILY -> date.dayOfWeek != DayOfWeek.SUNDAY
         Frequency.MONDAY_TO_FRIDAY -> date.dayOfWeek != DayOfWeek.SUNDAY && date.dayOfWeek != DayOfWeek.SATURDAY
         Frequency.WEEKLY -> date.dayOfWeek in task.weeklyDays!!
         else -> false
      }
   }

   private fun createDailySchedule(floorSchedule: FloorSchedule, task: Task, date: LocalDate): DailySchedule {
      val startInstant = date.atTime(task.startTime).atZone(ZoneId.of("America/Bogota")).toInstant()
      val endInstant = date.atTime(task.endTime).atZone(ZoneId.of("America/Bogota")).toInstant()
      return DailySchedule(
         task = task,
         employee = floorSchedule.responsible,
         schedule = floorSchedule.schedule,
         startTime = startInstant,
         endTime = endInstant,
         status = TaskStatus.PENDING,
         comments = null
      )
   }
}
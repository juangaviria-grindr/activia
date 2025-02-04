package com.gaviria.activia.services

import com.gaviria.activia.exceptions.ResourceNotFoundException
import com.gaviria.activia.models.dto.TaskDTO
import com.gaviria.activia.models.entities.Task
import com.gaviria.activia.repositories.FloorRepository
import com.gaviria.activia.repositories.TaskRepository
import org.springframework.stereotype.Service

@Service
class TaskService(
   private val floorRepository: FloorRepository,
   private val taskRepository: TaskRepository
) {

   fun getAllTasks(): List<Task> = taskRepository.findAll()

   fun getTasksByFloor(floorId: Long): List<Task> = taskRepository.findByFloorId(floorId)

   fun getTasksByFloors(floorIds: List<Long>): List<Task> = taskRepository.findByFloorIdIn(floorIds)

   fun createTask(taskDTO: TaskDTO): Task {
      val floor = floorRepository.findById(taskDTO.floorId).orElseThrow { ResourceNotFoundException("Floor not found") }
      val task = Task(
         name = taskDTO.name,
         startTime = taskDTO.startTime,
         endTime = taskDTO.endTime,
         frequency = taskDTO.frequency,
         weeklyDays = taskDTO.weeklyDays,
         floor = floor
      )
      return taskRepository.save(task)
   }

   fun deleteTask(id: Long) {
      if (taskRepository.existsById(id)) {
         taskRepository.deleteById(id)
      }
   }
}
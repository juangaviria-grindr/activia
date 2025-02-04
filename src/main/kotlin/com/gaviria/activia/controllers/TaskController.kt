package com.gaviria.activia.controllers

import com.gaviria.activia.models.dto.TaskDTO
import com.gaviria.activia.models.entities.Task
import com.gaviria.activia.services.TaskService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tasks")
class TaskController(private val taskService: TaskService) {

   @GetMapping
   fun getAllTasks(@RequestParam(required = false) floorIds: List<Long>?): List<Task> {
      return if (floorIds.isNullOrEmpty())
         taskService.getAllTasks()
      else
         taskService.getTasksByFloors(floorIds)
   }

   @GetMapping("/floor/{floorId}")
   fun getTasksByFloor(@PathVariable floorId: Long): List<Task> = taskService.getTasksByFloor(floorId)

   @PostMapping
   fun createTask(@Valid @RequestBody taskDTO: TaskDTO): Task {
      return taskService.createTask(taskDTO)
   }

   @DeleteMapping("/{id}")
   fun deleteTask(@PathVariable id: Long): ResponseEntity<String> {
      taskService.deleteTask(id)
      return ResponseEntity.ok("Task deleted successfully")
   }
}
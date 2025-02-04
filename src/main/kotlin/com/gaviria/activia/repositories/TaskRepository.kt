package com.gaviria.activia.repositories

import com.gaviria.activia.models.entities.Task
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskRepository : JpaRepository<Task, Long> {
   fun findByFloorId(floorId: Long): List<Task>
   fun findByFloorIdIn(floorIds: List<Long>): List<Task>
}
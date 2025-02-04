package com.gaviria.activia.repositories

import com.gaviria.activia.models.entities.FloorSchedule
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FloorScheduleRepository : JpaRepository<FloorSchedule, Long> {
   fun findByScheduleId(scheduleId: Long): List<FloorSchedule>
}
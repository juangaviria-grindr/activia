package com.gaviria.activia.repositories

import com.gaviria.activia.models.entities.ScheduleDetail
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ScheduleDetailRepository : JpaRepository<ScheduleDetail, Long> {
   fun findByScheduleId(scheduleId: Long): List<ScheduleDetail>
}
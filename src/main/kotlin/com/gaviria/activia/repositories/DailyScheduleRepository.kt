package com.gaviria.activia.repositories

import com.gaviria.activia.models.entities.DailySchedule
import java.time.LocalDate
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DailyScheduleRepository : JpaRepository<DailySchedule, Long> {
}
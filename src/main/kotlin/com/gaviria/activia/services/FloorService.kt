package com.gaviria.activia.services

import com.gaviria.activia.models.dto.FloorDTO
import com.gaviria.activia.models.entities.Floor
import com.gaviria.activia.repositories.FloorRepository
import org.springframework.stereotype.Service

@Service
class FloorService(private val floorRepository: FloorRepository) {

   fun getAllFloors(): List<Floor> = floorRepository.findAll()

   fun getFloorById(id: Long): Floor? = floorRepository.findById(id).orElse(null)

   fun createFloor(floorDTO: FloorDTO): Floor {
      val floor = Floor(name = floorDTO.name, description = floorDTO.description)
      return floorRepository.save(floor)
   }

   fun updateFloor(id: Long, floorDTO: FloorDTO): Floor? {
      val floor = getFloorById(id)
      return if (floor != null) {
         floor.name = floorDTO.name
         floor.description = floorDTO.description
         floorRepository.save(floor)
      } else {
         null
      }
   }

   fun deleteFloor(id: Long) {
      if (floorRepository.existsById(id)) {
         floorRepository.deleteById(id)
      }
   }
}
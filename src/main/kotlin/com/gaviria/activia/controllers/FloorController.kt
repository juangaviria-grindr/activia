package com.gaviria.activia.controllers

import com.gaviria.activia.models.dto.FloorDTO
import com.gaviria.activia.models.entities.Floor
import com.gaviria.activia.services.FloorService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/floors")
class FloorController(private val floorService: FloorService) {

   @GetMapping
   fun getAllFloors(): List<Floor> = floorService.getAllFloors()

   @GetMapping("/{id}")
   fun getFloorById(@PathVariable id: Long): ResponseEntity<Floor> {
      val floor = floorService.getFloorById(id)
      return if (floor != null) ResponseEntity.ok(floor) else ResponseEntity.notFound().build()
   }

   @PostMapping
   fun createFloor(@Valid @RequestBody floorDTO: FloorDTO): Floor {
      return floorService.createFloor(floorDTO)
   }

   @DeleteMapping("/{id}")
   fun deleteFloor(@PathVariable id: Long): ResponseEntity<String> {
      floorService.deleteFloor(id)
      return ResponseEntity.ok("Floor deleted successfully")
   }

   @PutMapping("/{id}")
   fun updateFloor(@PathVariable id: Long, @Valid @RequestBody floorDTO: FloorDTO): ResponseEntity<Floor> {
      val floor = floorService.updateFloor(id, floorDTO)
      return if (floor != null) ResponseEntity.ok(floor) else ResponseEntity.notFound().build()
   }
}
package com.gaviria.activia.exceptions

import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

   @ExceptionHandler(MethodArgumentNotValidException::class)
   fun handleValidationExceptions(ex: MethodArgumentNotValidException): ResponseEntity<Map<String, String>> {
      val errors = ex.bindingResult.fieldErrors.associate { it.field to it.defaultMessage.orEmpty() }
      return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
   }

   @ExceptionHandler(ConstraintViolationException::class)
   fun handleConstraintViolation(ex: ConstraintViolationException): ResponseEntity<Map<String, String>> {
      val errors = ex.constraintViolations.associate { it.propertyPath.toString() to it.message }
      return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
   }

   @ExceptionHandler(ResourceNotFoundException::class)
   fun handleNotFoundException(ex: ResourceNotFoundException): ResponseEntity<String> {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
   }

   @ExceptionHandler(BusinessException::class)
   fun handleBusinessException(ex: BusinessException): ResponseEntity<String> {
      return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.message)
   }

   @ExceptionHandler(Exception::class)
   fun handleGenericException(ex: Exception): ResponseEntity<String> {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred")
   }

   @ExceptionHandler(UnauthorizedException::class)
   fun handleUnauthorizedException(ex: UnauthorizedException): ResponseEntity<String> {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.message)
   }

   @ExceptionHandler(ForbiddenException::class)
   fun handleForbiddenException(ex: ForbiddenException): ResponseEntity<String> {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.message)
   }
}
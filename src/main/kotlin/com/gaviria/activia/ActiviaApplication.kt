package com.gaviria.activia

import com.gaviria.activia.models.dto.UserDTO
import com.gaviria.activia.models.entities.User
import com.gaviria.activia.models.enums.UserRole
import com.gaviria.activia.repositories.UserRepository
import com.gaviria.activia.services.UserService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.password.PasswordEncoder

@SpringBootApplication
class ActiviaApplication {

	@Bean
	fun init(userService: UserService) = CommandLineRunner {
		if (userService.getUserByEmail("admin@example.com") == null) {
			val user = UserDTO(email = "admin@example.com", password = "123456", role = UserRole.ADMIN)
			userService.createUser(user)
			println("✅ Usuario de prueba creado: admin@example.com / 123456")
		} else {
			println("🔹 Usuario ya existe, no se creó nuevamente.")
		}
	}
}

fun main(args: Array<String>) {
	runApplication<ActiviaApplication>(*args)
}

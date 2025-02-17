package com.gaviria.activia

import com.gaviria.activia.models.entities.User
import com.gaviria.activia.models.enums.UserRole
import com.gaviria.activia.repositories.UserRepository
import com.gaviria.activia.services.UserService
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.password.PasswordEncoder

@SpringBootApplication
class ActiviaApplication {

	val logger = LoggerFactory.getLogger(ActiviaApplication::class.java)

	@Bean
	fun init(userService: UserService) = CommandLineRunner {
		if (userService.getUserByEmail("admin@example.com") == null) {
			userService.createUser("admin@example.com", UserRole.ADMIN, "123456")
			logger.info("✅ Usuario de prueba creado: admin@example.com / 123456")
		} else {
			logger.info("🔹 Usuario ya existe, no se creó nuevamente.")
		}
	}
}

fun main(args: Array<String>) {
	runApplication<ActiviaApplication>(*args)
}

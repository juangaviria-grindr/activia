package com.gaviria.activia.config

import com.gaviria.activia.security.JwtAuthenticationFilter
import io.jsonwebtoken.security.Keys
import javax.crypto.SecretKey
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
class SecurityConfig(private val jwtAuthenticationFilter: JwtAuthenticationFilter) {

   @Value("\${jwt.secret}")
   private lateinit var jwtSecret: String

   @Bean
   fun secretKey(): SecretKey {
      return Keys.hmacShaKeyFor(jwtSecret.toByteArray())
   }

   @Bean
   fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
      http
         .cors { it.configurationSource(corsConfigurationSource()) }
         .csrf { it.disable() }
         .authorizeHttpRequests { auth ->
            auth.requestMatchers("/auth/login").permitAll() // 🔓 Ruta pública
               .anyRequest().authenticated() // 🔒 Rutas protegidas
         }
         .sessionManagement { session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
         .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)

      return http.build()
   }

   @Bean
   fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder(12)

   @Bean
   fun corsConfigurationSource(): UrlBasedCorsConfigurationSource {
      val configuration = CorsConfiguration()
      configuration.allowedOrigins = listOf("http://localhost:4200") //TODO: Esto debe estar en un archivo de configuración
      configuration.allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "OPTIONS")
      configuration.allowedHeaders = listOf("Authorization", "Content-Type")
      configuration.allowCredentials = true
      val source = UrlBasedCorsConfigurationSource()
      source.registerCorsConfiguration("/**", configuration)
      return source
   }
}
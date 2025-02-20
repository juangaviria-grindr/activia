package com.gaviria.activia.security

import com.gaviria.activia.models.enums.UserRole
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*

@Component
class JwtUtil {

   private val secret : String = "MiClaveSecretaMuyLargaYSegura123456"
   private val secretKey: Key = Keys.hmacShaKeyFor(secret.toByteArray(Charsets.UTF_8))

   fun generateToken(userId: Long, email: String, role: UserRole): String {
      return Jwts.builder()
         .claim("email", email)
         .claim("role", role)
         .claim("id", userId)
         .issuedAt(Date())
         .expiration(Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hora
         .signWith(secretKey)
         .compact()
   }

   fun extractEmail(token: String): String {
      return getClaims(token).get("email", String::class.java)
   }

   fun extractId(token: String): Long {
      return getClaims(token).get("id", Integer::class.java).toLong()
   }

   fun extractRole(token: String): UserRole {
      return UserRole.valueOf(getClaims(token).get("role", String::class.java))
   }

   fun isTokenValid(token: String): Boolean {
      return try {
         val claims = getClaims(token)
         !claims.expiration.before(Date())
      } catch (e: Exception) {
         false
      }
   }

   private fun getClaims(token: String): Claims {
      return Jwts.parser()
         .setSigningKey(secretKey)
         .build()
         .parseSignedClaims(token)
         .payload
   }
}
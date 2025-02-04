package com.gaviria.activia.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*

@Component
class JwtUtil {

   private val secretKey: Key = Keys.secretKeyFor(SignatureAlgorithm.HS256) // 🔥 Se genera una clave segura

   fun generateToken(email: String): String {
      return Jwts.builder()
         .subject(email)
         .issuedAt(Date())
         .expiration(Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hora
         .signWith(secretKey)
         .compact()
   }

   fun extractEmail(token: String): String {
      return getClaims(token).subject
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
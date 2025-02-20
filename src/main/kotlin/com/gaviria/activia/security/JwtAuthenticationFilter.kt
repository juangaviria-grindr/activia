package com.gaviria.activia.security

import com.gaviria.activia.models.dto.Principal
import com.gaviria.activia.models.entities.User
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.security.SignatureException
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import java.io.IOException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(private val jwtUtil: JwtUtil) : OncePerRequestFilter() {

   @Throws(ServletException::class, IOException::class)
   override fun doFilterInternal(
      request: HttpServletRequest,
      response: HttpServletResponse,
      chain: FilterChain
   ) {
      val authHeader = request.getHeader("Authorization")

      if (authHeader != null && authHeader.startsWith("Bearer ")) {
         val token = authHeader.substring(7)
         try {
            if (jwtUtil.isTokenValid(token)) {
               val id = jwtUtil.extractId(token)
               val email = jwtUtil.extractEmail(token)
               val role = jwtUtil.extractRole(token)
               val authorities = listOf(SimpleGrantedAuthority("ROLE_$role"))
               val authToken = UsernamePasswordAuthenticationToken(
                  Principal(id, email, role),
                  null,
                  authorities
               )
               authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
               SecurityContextHolder.getContext().authentication = authToken
            } else {
               response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token")
               return
            }
         } catch (e: ExpiredJwtException) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token expired")
            return
         } catch (e: SignatureException) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid signature")
            return
         } catch (e: MalformedJwtException) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token")
            return
         }
      }

      chain.doFilter(request, response)
   }
}
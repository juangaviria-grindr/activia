package com.gaviria.activia.security

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.security.SignatureException
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException

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
               val email = jwtUtil.extractEmail(token)
               val authToken = UsernamePasswordAuthenticationToken(
                  User(email, "", emptyList()),
                  null,
                  emptyList()
               )
               authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
               SecurityContextHolder.getContext().authentication = authToken
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
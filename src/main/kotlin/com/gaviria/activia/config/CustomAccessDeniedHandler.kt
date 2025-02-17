package com.gaviria.activia.config

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component

@Component
class CustomAccessDeniedHandler : AccessDeniedHandler {
   override fun handle(
      request: HttpServletRequest?,
      response: HttpServletResponse?,
      accessDeniedException: org.springframework.security.access.AccessDeniedException?
   ) {
      response?.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied")
   }
}
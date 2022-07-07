package jp.ac.itcollege.s21010.newsrelease.presentation.handler

import org.springframework.security.web.access.AccessDeniedHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class NewsManagerAccessDeniedHandler : AccessDeniedHandler {
    override fun handle(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        accessDeniedException: org.springframework.security.access.AccessDeniedException?,
    ) {
        response?.apply {
            status = HttpServletResponse.SC_FORBIDDEN
        }
    }
}
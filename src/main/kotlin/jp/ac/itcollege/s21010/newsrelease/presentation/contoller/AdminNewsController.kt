package jp.ac.itcollege.s21010.newsrelease.presentation.contoller

import jp.ac.itcollege.s21010.newsrelease.application.security.NewsManagerUserDetails
import jp.ac.itcollege.s21010.newsrelease.application.service.AdminNewsService
import jp.ac.itcollege.s21010.newsrelease.infrastructure.database.record.News
import jp.ac.itcollege.s21010.newsrelease.presentation.form.RegisterNewsRequest
import org.apache.tomcat.util.net.openssl.ciphers.Authentication
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("admin/news")
@CrossOrigin(origins = ["http://localhost:8081"], allowCredentials = "true")
class AdminNewsController(
    private val adminNewsService: AdminNewsService,
) {
    @PostMapping("/register")
    fun register(@RequestBody request: RegisterNewsRequest, authentication: Authentication) {
        adminNewsService.register(
            News(
                0,
                request.title,
                request.categoryId,
                request.publishAt,
                LocalDateTime.now(),
                1,
                request.body
            )
        )
    }
}
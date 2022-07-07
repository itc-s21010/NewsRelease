package jp.ac.itcollege.s21010.newsrelease.application.service

import jp.ac.itcollege.s21010.newsrelease.domain.repository.UserRepository
import jp.ac.itcollege.s21010.newsrelease.infrastructure.database.record.Users
import org.springframework.stereotype.Service

@Service
class AuthenticationService(private val userRepository: UserRepository) {
    fun findUsers(username: String): Users? {
        return userRepository.find(username)
    }
}
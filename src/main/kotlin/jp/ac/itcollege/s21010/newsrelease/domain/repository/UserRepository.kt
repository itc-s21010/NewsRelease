package jp.ac.itcollege.s21010.newsrelease.domain.repository

import jp.ac.itcollege.s21010.newsrelease.infrastructure.database.record.Users

interface UserRepository {
    fun find(email: String) : Users?
}
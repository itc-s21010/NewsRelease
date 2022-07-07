/*
 * Auto-generated file. Created by MyBatis Generator
 */
package jp.ac.itcollege.s21010.newsrelease.infrastructure.database.record

import java.time.LocalDateTime

data class News(
    var id: Long? = null,
    var title: String? = null,
    var categoryId: Long? = null,
    var publishAt: LocalDateTime? = null,
    var createAt: LocalDateTime? = null,
    var userId: Long? = null,
    var body: String? = null
)
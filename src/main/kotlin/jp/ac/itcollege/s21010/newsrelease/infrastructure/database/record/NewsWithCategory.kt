package jp.ac.itcollege.s21010.newsrelease.infrastructure.database.record

import java.sql.Timestamp


data class NewsWithCategory(
        val id: Long? = null,
        val title: String? = null,
        val categoryId: Long? = null,
        val createAt: Timestamp? = null,
        val publishAt: Timestamp? = null,
        val userId: Long? = null,
        val body: String? = null,
        val name: String? = null,
    )

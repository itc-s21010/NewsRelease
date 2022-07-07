package jp.ac.itcollege.s21010.newsrelease.domain.repository

import jp.ac.itcollege.s21010.newsrelease.domain.model.NewsWithCategoryModel
import jp.ac.itcollege.s21010.newsrelease.infrastructure.database.record.News

interface NewsRepository {
    fun findAllWithCategory(): List<NewsWithCategoryModel>

    fun findWithCategory(id: Long): NewsWithCategoryModel?

    fun register(news: News)
}
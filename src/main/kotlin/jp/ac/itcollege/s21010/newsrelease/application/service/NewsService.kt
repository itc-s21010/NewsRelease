package jp.ac.itcollege.s21010.newsrelease.application.service

import jp.ac.itcollege.s21010.newsrelease.domain.model.NewsWithCategoryModel
import jp.ac.itcollege.s21010.newsrelease.domain.repository.NewsRepository
import org.springframework.stereotype.Service

@Service
class NewsService(
    private val newsRepository: NewsRepository
){
    fun getList(): List<NewsWithCategoryModel> {
        return newsRepository.findAllWithCategory()
    }

    fun getDetail(newsId: Long): NewsWithCategoryModel {
        return newsRepository.findWithCategory(newsId) ?: throw IllegalArgumentException("存在しないニュースID: $newsId")
    }
}
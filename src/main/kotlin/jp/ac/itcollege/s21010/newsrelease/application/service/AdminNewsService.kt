package jp.ac.itcollege.s21010.newsrelease.application.service

import jp.ac.itcollege.s21010.newsrelease.domain.repository.NewsRepository
import jp.ac.itcollege.s21010.newsrelease.infrastructure.database.record.News
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminNewsService(
    private val newsRepository: NewsRepository
){
    @Transactional
    fun register(news: News) {
        newsRepository.findWithCategory(news.id!!)?.let { throw IllegalArgumentException("すでに存在するニュースID: ${news.id}")
        }
        newsRepository.register(news)
    }
}
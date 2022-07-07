package jp.ac.itcollege.s21010.newsrelease.infrastructure.database.repository

import jp.ac.itcollege.s21010.newsrelease.domain.model.CategoryModel
import jp.ac.itcollege.s21010.newsrelease.domain.model.NewsModel
import jp.ac.itcollege.s21010.newsrelease.domain.model.NewsWithCategoryModel
import jp.ac.itcollege.s21010.newsrelease.domain.repository.NewsRepository
import jp.ac.itcollege.s21010.newsrelease.infrastructure.database.mapper.*
import jp.ac.itcollege.s21010.newsrelease.infrastructure.database.record.News
import jp.ac.itcollege.s21010.newsrelease.infrastructure.database.record.NewsWithCategory
import org.springframework.stereotype.Repository

@Repository
class NewsRepositoryImpl(
    private val newsWithCategoryMapper: NewsWithCategoryMapper,
    private val newsMapper: NewsMapper
)  : NewsRepository {
    override fun findAllwithCategory(): List<NewsWithCategoryModel> {
        return newsWithCategoryMapper.select{ }.map { toModel(it) }
    }

    override fun findWithCategory(id: Long): NewsWithCategoryModel? {
        return newsWithCategoryMapper.selectByPrimarykey(id)?.let { toModel(it) }
    }

    override fun register(news: News) {
        newsMapper.insert(toCategory(news))
    }

    private fun toModel(record: NewsWithCategory): NewsWithCategoryModel {
        val news = NewsModel(
            record.id!!,
            record.title!!,
            record.categoryId!!,
            record.publishAt!!,
            record.createAt!!,
            record.userId!!,
            record.body!!

        )
        val category = record.id?.let {
            CategoryModel(
                record.id!!,
                record.name!!
            )
        }
        return NewsWithCategoryModel(news, category)
    }

    private fun toCategory(model: News) : News {
        return News(model.id, model.title, model.categoryId, model.publishAt, model.createAt, model.userId, model.body)
    }

}

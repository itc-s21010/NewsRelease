package jp.ac.itcollege.s21010.newsrelease.domain.model

data class NewsWithCategoryModel(
    val news: NewsModel,
    val category: CategoryModel?
){
    val isCategory: Boolean
        get() = category != null
}
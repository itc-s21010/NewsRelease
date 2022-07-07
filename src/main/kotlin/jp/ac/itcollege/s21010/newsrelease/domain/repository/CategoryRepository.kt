package jp.ac.itcollege.s21010.newsrelease.domain.repository

import jp.ac.itcollege.s21010.newsrelease.domain.model.CategoryModel

interface CategoryRepository {
    fun register(category: CategoryModel)

    fun update(id: Long, name: String)

    fun delete(id: Long)

}
/*
 * Auto-generated file. Created by MyBatis Generator
 */
package jp.ac.itcollege.s21010.newsrelease.infrastructure.database.record

import jp.ac.itcollege.s21010.newsrelease.domain.enum.RoleType

data class Users(
    var id: Long? = null,
    var username: String? = null,
    var password: String? = null,
    var viewName: String? = null,
    var roleType: RoleType? = null
)
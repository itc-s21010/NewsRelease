package jp.ac.itcollege.s21010.newsrelease.infrastructure.database.repository

import jp.ac.itcollege.s21010.newsrelease.domain.repository.UserRepository
import jp.ac.itcollege.s21010.newsrelease.infrastructure.database.mapper.UsersDynamicSqlSupport
import jp.ac.itcollege.s21010.newsrelease.infrastructure.database.mapper.UsersMapper
import jp.ac.itcollege.s21010.newsrelease.infrastructure.database.mapper.selectOne
import jp.ac.itcollege.s21010.newsrelease.infrastructure.database.record.Users
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl(
    private val mapper: UsersMapper,
) : UserRepository {
    override fun find(email: String): Users? {
        val record = mapper.selectOne {
            where {
                UsersDynamicSqlSupport.users.username isEqualTo email
            }
        }
        return record?.let { toModel(it) }
    }

    private fun toModel(record: Users) : Users {
        return Users(
            record.id!!,
            record.username!!,
            record.password!!,
            record.viewName!!,
            record.roleType!!
        )
    }

}
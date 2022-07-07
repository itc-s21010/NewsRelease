package jp.ac.itcollege.s21010.newsrelease.application.security

import jp.ac.itcollege.s21010.newsrelease.application.service.AuthenticationService
import jp.ac.itcollege.s21010.newsrelease.domain.enum.RoleType
import jp.ac.itcollege.s21010.newsrelease.infrastructure.database.record.Users
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService

class NewsManagerUserDetailsService(
    private val authenticationService: AuthenticationService
): UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails? {
        val users = authenticationService.findUsers(username?: "")
        return users?.let { NewsManagerUserDetails(users) }
    }
}

data class NewsManagerUserDetails(val id: Long?, val user: String, val pass: String, val viewName: String?, val roleType: RoleType?
): UserDetails {
    constructor(users: Users) : this(
        users.id, users.username!!, users.password!!, users.viewName, users.roleType
    )
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return AuthorityUtils.createAuthorityList(this.roleType.toString())
    }

    override fun getPassword(): String {
        return this.pass
    }

    override fun getUsername(): String {
        return this.user
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

}

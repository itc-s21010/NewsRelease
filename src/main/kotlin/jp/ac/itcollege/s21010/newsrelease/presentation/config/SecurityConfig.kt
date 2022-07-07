package jp.ac.itcollege.s21010.newsrelease.presentation.config

import jp.ac.itcollege.s21010.newsrelease.application.security.NewsManagerUserDetailsService
import jp.ac.itcollege.s21010.newsrelease.application.service.AuthenticationService
import jp.ac.itcollege.s21010.newsrelease.domain.enum.RoleType
import jp.ac.itcollege.s21010.newsrelease.presentation.handler.NewsManagerAccessDeniedHandler
import jp.ac.itcollege.s21010.newsrelease.presentation.handler.NewsManagerAuthenticationEntryPoint
import jp.ac.itcollege.s21010.newsrelease.presentation.handler.NewsManagerAuthenticationFailureHandler
import jp.ac.itcollege.s21010.newsrelease.presentation.handler.NewsManagerAuthenticationSuccessHandler
import org.springframework.context.annotation.Bean
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@EnableWebSecurity
class SecurityConfig(private val authenticatorService: AuthenticationService) {

    @Bean
    @Order(1)
    fun configure(http: HttpSecurity): SecurityFilterChain {
        http.authorizeRequests {
            it
                .mvcMatchers("/login").permitAll()
                .mvcMatchers("/news/list").permitAll()
                .mvcMatchers("/admin/**").hasAuthority(RoleType.ADMIN.toString())
                .anyRequest().authenticated()
        }.formLogin{
            it
                .loginProcessingUrl("/login")
                .usernameParameter("user")
                .passwordParameter("pass")
                .successHandler(NewsManagerAuthenticationSuccessHandler())
                .failureHandler(NewsManagerAuthenticationFailureHandler())
        }.csrf {
            it
                .disable()
        }.exceptionHandling{
            it
                .authenticationEntryPoint(NewsManagerAuthenticationEntryPoint())
                .accessDeniedHandler(NewsManagerAccessDeniedHandler())
        }.cors {
            it
                .configurationSource(corsConfigurationSource())
        }

        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun userDetailsService(): UserDetailsService = NewsManagerUserDetailsService(authenticatorService)

    private fun corsConfigurationSource(): CorsConfigurationSource {
        val config = CorsConfiguration().apply {
            addAllowedMethod(CorsConfiguration.ALL)
            addAllowedHeader(CorsConfiguration.ALL)
            addAllowedOrigin("http://localhost:8081/")
            allowCredentials = true
        }

        val source = UrlBasedCorsConfigurationSource().apply {
            registerCorsConfiguration("/**", config)
        }

        return source
    }
}
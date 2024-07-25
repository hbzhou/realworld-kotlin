package com.itsz.realworld.kotlin.configuration

import com.itsz.realworld.kotlin.core.user.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AnonymousAuthenticationProvider
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableMethodSecurity
class SecurityConfig {

    @Bean
    fun authenticationProvider(userService: UserService, passwordEncoder: PasswordEncoder): DaoAuthenticationProvider {
        return DaoAuthenticationProvider().apply {
            setUserDetailsService(userService)
            setPasswordEncoder(passwordEncoder)
        }
    }

    @Bean
    fun authenticationManager(authenticationProviders: List<AuthenticationProvider>): AuthenticationManager {
        return ProviderManager(authenticationProviders)
    }

    @Bean
    fun anonymousAuthenticationProvider(): AuthenticationProvider {
        return AnonymousAuthenticationProvider("securityKey")
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http.authorizeHttpRequests {
            it.requestMatchers("/api/auth/*").permitAll().anyRequest().authenticated()
        }
            .formLogin { it.disable() }
            .cors { it.disable() }
            .httpBasic { it.disable() }
            .build()
    }


}
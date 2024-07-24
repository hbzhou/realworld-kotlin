package com.itsz.realworld.kotlin.core.user

import org.springframework.security.core.userdetails.UserDetailsService

interface UserService: UserDetailsService {

    fun findAll(): List<User>
}
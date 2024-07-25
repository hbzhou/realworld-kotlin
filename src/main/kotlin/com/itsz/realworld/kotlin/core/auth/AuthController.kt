package com.itsz.realworld.kotlin.core.auth

import com.itsz.realworld.kotlin.core.user.UserService
import org.apache.coyote.BadRequestException
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User as SecurityUser
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(val userService: UserService, val authenticationManager: AuthenticationManager) {

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest):ResponseEntity<SecurityUser> {
        val (username, password) = request
        val user = userService.loadUserByUsername(username)?:throw BadRequestException("user not found with username: $username")
        val token = UsernamePasswordAuthenticationToken(user, password)
        val authentication = authenticationManager.authenticate(token)
        SecurityContextHolder.getContext().authentication = authentication
        return ResponseEntity.ok().body(authentication.principal as SecurityUser)
    }
}

data class LoginRequest(val username:String, val password:String)
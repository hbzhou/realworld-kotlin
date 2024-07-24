package com.itsz.realworld.kotlin.core.user

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(val userService: UserService) {

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    fun getUsers(): List<User> = userService.findAll()

}
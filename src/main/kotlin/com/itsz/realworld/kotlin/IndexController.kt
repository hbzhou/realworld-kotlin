package com.itsz.realworld.kotlin

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class IndexController {

    @GetMapping("/hello")
    @PreAuthorize("hasAuthority('READ')")
    fun index() = "hello world"
}
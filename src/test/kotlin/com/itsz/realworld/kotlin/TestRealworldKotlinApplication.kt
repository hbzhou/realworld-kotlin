package com.itsz.realworld.kotlin

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
	fromApplication<RealworldKotlinApplication>().with(TestcontainersConfiguration::class).run(*args)
}

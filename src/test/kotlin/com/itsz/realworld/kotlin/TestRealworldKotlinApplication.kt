package com.itsz.realworld.kotlin

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
	fromApplication<RealWorldKotlinApplication>().with(TestcontainersConfiguration::class).run(*args)
}

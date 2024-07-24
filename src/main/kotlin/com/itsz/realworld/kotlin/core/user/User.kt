package com.itsz.realworld.kotlin.core.user

import jakarta.persistence.*

@Entity
@Table(name = "T_USERS")
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long?  = null
    var username: String? = null
    var password: String? = null
    var email: String? = null
    var gender: Int? = null
}
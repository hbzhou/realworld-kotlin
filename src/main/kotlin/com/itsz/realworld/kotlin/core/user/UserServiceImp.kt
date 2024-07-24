package com.itsz.realworld.kotlin.core.user

import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.security.core.userdetails.User as SecurityUser

@Service
class UserServiceImp(val userRepository: UserRepository, val passwordEncoder: PasswordEncoder): UserService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username)?: throw UsernameNotFoundException("User not found: $username")
        return with(user){
            SecurityUser.withUsername(this.username)
                .password(passwordEncoder.encode(password))
                .authorities(AuthorityUtils.commaSeparatedStringToAuthorityList("READ,WRITE"))
                .build()
        }
    }

   override fun findAll(): List<User> = userRepository.findAll()
}
package com.member

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories
class MemberApplication {
}

fun main(args: Array<String>) {
    runApplication<MemberApplication>(*args)
}

package com.member

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackageClasses = [MemberSystemExampleApplication::class])
class MemberSystemExampleApplication {
}

fun main(args: Array<String>) {
    runApplication<MemberSystemExampleApplication>(*args)
}
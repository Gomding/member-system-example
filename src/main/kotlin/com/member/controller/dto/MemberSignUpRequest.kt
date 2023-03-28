package com.member.controller.dto

import com.member.persistence.Member

data class MemberSignUpRequest(
    var name: String,
    var age: Int,
) {
    fun toEntity(): Member {
        return Member(
            name = name,
            age = age
        )
    }
}

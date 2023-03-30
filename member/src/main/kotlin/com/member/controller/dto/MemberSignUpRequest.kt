package com.member.controller.dto

import com.member.domain.Member

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

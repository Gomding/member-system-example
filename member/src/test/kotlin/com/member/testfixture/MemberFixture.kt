package com.member.testfixture

import com.member.domain.Member

object MemberFixture {
    fun create(
        id: Long = 0L,
        name: String = "memberName",
        age: Int = 13,
    ): Member {
        return Member(
            id = id,
            name = name,
            age = age,
        )
    }
}
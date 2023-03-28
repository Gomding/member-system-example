package com.member.testfixture

import com.member.persistence.Member

object MemberFixture {
    fun create(): Member {
        return Member(
            id = 1,
            name = "memberName",
            age = 13,
        )
    }
}
package com.member.domain

import org.springframework.data.jpa.repository.JpaRepository

interface MemberEventRepository : JpaRepository<MemberEvent, Long> {
    fun findByMemberId(signUpMemberId: Long): MemberEvent?

    fun findByMemberIdAndEventType(signUpMemberId: Long, eventType: MemberEventType): MemberEvent?
}
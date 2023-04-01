package com.member.service

import com.member.domain.Member
import com.member.domain.MemberEvent
import com.member.domain.MemberEventRepository
import com.member.domain.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MemberService(
    private val memberRepository: MemberRepository,
    private val memberEventRepository: MemberEventRepository,
    private val eventPublisher: EventPublisher,
) {
    fun signUp(member: Member): Long {
        val savedMember = memberRepository.save(member)
        val memberId = savedMember.id
        eventPublisher.signUp(MemberSignUpEvent(memberId))
        memberEventRepository.save(MemberEvent.createSignUpEvent(memberId))
        return memberId
    }
}
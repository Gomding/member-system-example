package com.member.service

import com.member.domain.Member
import com.member.domain.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MemberService(
    private val memberRepository: MemberRepository,
    private val eventPublisher: EventPublisher,
) {
    fun signUp(member: Member): Long {
        val savedMember = memberRepository.save(member)
        eventPublisher.signUp(MemberSignUpEvent(savedMember.id))
        return savedMember.id
    }
}
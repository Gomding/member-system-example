package com.member.service

import com.member.persistence.Member
import com.member.persistence.MemberRepository
import com.member.service.event.MemberSignUpEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MemberService(
    private val memberRepository: MemberRepository,
    private val publisher: ApplicationEventPublisher,
) {
    fun signUp(member: Member): Long {
        val savedMember = memberRepository.save(member)
        publisher.publishEvent(MemberSignUpEvent(savedMember.id))
        return savedMember.id
    }
}
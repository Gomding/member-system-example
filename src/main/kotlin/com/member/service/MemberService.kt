package com.member.service

import com.member.persistence.Member
import com.member.persistence.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MemberService(
    private val memberRepository: MemberRepository,
) {
    fun signUp(member: Member): Long {
        val savedMember = memberRepository.save(member)
        return savedMember.id
    }
}
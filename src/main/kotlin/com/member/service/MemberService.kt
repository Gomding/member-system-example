package com.member.service

import com.member.persistence.Member
import com.member.persistence.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MemberService(
    private val memberRepository: MemberRepository,
    private val couponService: CouponService,
) {
    fun signUp(member: Member): Long {
        val savedMember = memberRepository.save(member)
        couponService.signUpWelcomeCoupon(savedMember)
        return savedMember.id
    }
}
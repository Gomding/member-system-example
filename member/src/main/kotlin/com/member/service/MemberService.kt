package com.member.service

import com.member.domain.Member
import com.member.domain.MemberRepository
import com.member.internal.CouponClient
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MemberService(
    private val memberRepository: MemberRepository,
    private val couponClient: CouponClient,
) {
    fun signUp(member: Member): Long {
        val savedMember = memberRepository.save(member)
        couponClient.publishSignUpCoupon(savedMember.id)
        return savedMember.id
    }
}
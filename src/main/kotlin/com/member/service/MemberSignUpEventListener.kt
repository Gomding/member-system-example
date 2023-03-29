package com.member.service

import com.member.service.event.MemberSignUpEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class MemberSignUpEventListener(
    private val couponService: CouponService,
) {

    @EventListener
    fun publishSignUpWelcomeCoupon(memberSignUpEvent: MemberSignUpEvent) {
        couponService.signUpWelcomeCoupon(memberSignUpEvent.memberId)
    }
}
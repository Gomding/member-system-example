package com.member.service

import com.member.persistence.*
import org.springframework.stereotype.Service

@Service
class CouponService(
    val couponRepository: CouponRepository,
    val couponTypeRepository: CouponTypeRepository,
) {
    fun signUpWelcomeCoupon(member: Member): Coupon {
        val signUpWelcomeCouponType =
            couponTypeRepository.findCouponTypeByCode(code = CouponCode.SIGN_UP_WELCOME.couponCode)
        return issueCoupon(member = member, couponType = signUpWelcomeCouponType)
    }

    private fun issueCoupon(member: Member, couponType: CouponType): Coupon {
        val coupon = Coupon.of(couponType)
        coupon.issueCoupon(member)
        validateIssueCoupon(coupon)
        val savedCoupon = couponRepository.save(coupon)
        return savedCoupon
    }

    private fun validateIssueCoupon(coupon: Coupon) {
        if (coupon.memberId <= 0L) {
            throw IllegalArgumentException("쿠폰 발급 대상이 존재하지 않습니다. memberId: ${coupon.memberId}")
        }
    }
}
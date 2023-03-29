package com.member.service

import com.member.persistence.*
import org.springframework.stereotype.Service

@Service
class CouponService(
    val couponRepository: CouponRepository,
    val couponTypeRepository: CouponTypeRepository,
    val currentDateTimeService: CurrentDateTimeService,
) {
    fun signUpWelcomeCoupon(member: Member): Coupon {
        val signUpWelcomeCouponType =
            couponTypeRepository.findCouponTypeByCode(code = CouponCode.SIGN_UP_WELCOME.couponCode)
        return publishCoupon(member = member, couponType = signUpWelcomeCouponType)
    }

    private fun publishCoupon(member: Member, couponType: CouponType): Coupon {
        val coupon = Coupon.publishCoupon(
            couponType = couponType,
            member = member,
            validStartDate = currentDateTimeService.localDateNow(),
        )
        validatePublishCoupon(coupon)
        val savedCoupon = couponRepository.save(coupon)
        return savedCoupon
    }

    private fun validatePublishCoupon(coupon: Coupon) {
        if (coupon.memberId <= INVALID_MEMBER_ID) {
            throw IllegalArgumentException("쿠폰 발급 대상이 존재하지 않습니다. memberId: ${coupon.memberId}")
        }
    }

    companion object {
        private const val INVALID_MEMBER_ID = 0L
    }
}
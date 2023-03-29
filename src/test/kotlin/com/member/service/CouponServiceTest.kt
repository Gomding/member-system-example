package com.member.service

import com.member.persistence.CouponCode
import com.member.testfixture.MemberFixture
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest

private const val SIGN_UP_WELCOME_COUPON_VALID_DAYS = 14L

@SpringBootTest
internal class CouponServiceTest(
    private val sut: CouponService,
    private val currentDateTimeServiceStub: CurrentDateTimeServiceStub,
) : FunSpec({
    test("신규 가입 환영 쿠폰을 발급한다") {
        // given
        val member = MemberFixture.create()

        // when
        val coupon = sut.signUpWelcomeCoupon(member)

        // then
        val expectValidStartDate = currentDateTimeServiceStub.localDateNow()
        coupon.couponCode shouldBe CouponCode.SIGN_UP_WELCOME.couponCode
        coupon.validStartDate shouldBe expectValidStartDate
        coupon.validLastDate shouldBe expectValidStartDate.plusDays(SIGN_UP_WELCOME_COUPON_VALID_DAYS)
        coupon.memberId shouldBe member.id
        currentDateTimeServiceStub.isUsedLocalDateNow shouldBe true
    }
})


package com.member.service

import com.member.persistence.CouponCode
import com.member.testfixture.MemberFixture
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class CouponServiceTest(
    private val sut: CouponService,
) : FunSpec({
    test("신규 가입 환영 쿠폰을 발급한다") {
        // given
        val member = MemberFixture.create()

        // when
        val coupon = sut.signUpWelcomeCoupon(member)

        // then
        coupon.couponCode shouldBe CouponCode.SIGN_UP_WELCOME.couponCode
        coupon.memberId shouldBe member.id
    }
})

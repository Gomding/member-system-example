package com.member.persistence

import com.member.testfixture.CouponTypeFixture
import com.member.testfixture.MemberFixture
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

internal class CouponTest: FunSpec({
    test("CouponType 정보로 Coupon 객체를 생성할 수 있다.") {
        // given
        val couponType = CouponTypeFixture.create()

        // when
        val coupon = Coupon.of(couponType)

        // then
        coupon shouldNotBe null
        coupon.couponCode shouldBe couponType.code
        coupon.memberId shouldBe 0
    }

    test("쿠폰을 멤버에게 발급한다") {
        // given
        val couponType = CouponTypeFixture.create()
        val member = MemberFixture.create()
        val sut = Coupon.of(couponType)

        // when
        sut.issueCoupon(member)

        // then
        sut.memberId shouldBe member.id
    }
})

package com.member.persistence

import com.member.testfixture.CouponTypeFixture
import com.member.testfixture.MemberFixture
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import java.time.LocalDate

internal class CouponTest: FunSpec({
    test("CouponType 정보로 Coupon 객체를 생성할 수 있다.") {
        // given
        val couponType = CouponTypeFixture.create()
        val member = MemberFixture.create()
        val validStartDate = LocalDate.of(2023, 3, 7)

        // when
        val coupon = Coupon.publishCoupon(couponType = couponType, memberId = member.id, validStartDate = validStartDate)

        // then
        coupon shouldNotBe null
        coupon.couponCode shouldBe couponType.code
        coupon.validStartDate shouldBe validStartDate
        coupon.validLastDate shouldBe validStartDate.plusDays(couponType.validDays)
        coupon.memberId shouldBe member.id
    }
})

package com.member.service

import com.member.persistence.CouponCode
import com.member.persistence.CouponRepository
import com.member.testfixture.MemberFixture
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class MemberServiceTest(
    private val sut: MemberService,
    private val couponRepository: CouponRepository,
) : FunSpec({

    test("회원가입이 가능하다") {
        // given
        val member = MemberFixture.create(id = 0)

        // when
        val signUpMemberId = sut.signUp(member)

        // then
        signUpMemberId shouldNotBe 0
    }

    test("회원가입 시 신규회원 가입 쿠폰을 발급한다.") {
        // given
        val member = MemberFixture.create(id = 0)

        // when
        val signUpMemberId = sut.signUp(member)

        // then
        val coupon = couponRepository.findByMemberId(signUpMemberId)
        coupon shouldNotBe null
        coupon?.couponCode shouldBe CouponCode.SIGN_UP_WELCOME.couponCode
    }
})

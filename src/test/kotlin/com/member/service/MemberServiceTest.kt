package com.member.service

import com.member.persistence.CouponRepository
import com.member.service.event.MemberSignUpEvent
import com.member.testfixture.MemberFixture
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@SpringBootTest
internal class MemberServiceTest(
    private val sut: MemberService,
    private val couponRepository: CouponRepository,
    private val memberSignUpTestEventListener: MemberSignUpTestEventListener
) : FunSpec({

    test("회원가입이 가능하다") {
        // given
        val member = MemberFixture.create(id = 0)

        // when
        val signUpMemberId = sut.signUp(member)

        // then
        signUpMemberId shouldNotBe 0
    }

    test("회원가입 시 회원가입 이벤트를 발행한다.") {
        // given
        val member = MemberFixture.create(id = 0)

        // when
        val signUpMemberId = sut.signUp(member)

        // then
        memberSignUpTestEventListener.isMemberSignUpPublished shouldBe true
    }
})

@Component
class MemberSignUpTestEventListener(
    var isMemberSignUpPublished: Boolean = false,
) {
    @EventListener
    fun publishMemberSignUpEvent(memberSignUpEvent: MemberSignUpEvent) {
        this.isMemberSignUpPublished = true
    }
}

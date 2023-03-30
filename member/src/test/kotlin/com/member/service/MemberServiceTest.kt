package com.member.service

import com.member.testfixture.MemberFixture
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class MemberServiceTest(
    private val sut: MemberService,
) : FunSpec({

    test("회원가입이 가능하다") {
        // given
        val member = MemberFixture.create(id = 0)

        // when
        val signUpMemberId = sut.signUp(member)

        // then
        signUpMemberId shouldNotBe 0
    }

//    test("회원가입 시 회원가입 이벤트를 발행한다.") {
//        // given
//        val member = MemberFixture.create(id = 0)
//
//        // when
//        val signUpMemberId = sut.signUp(member)
//
//        // then
//        memberSignUpTestEventListener.isMemberSignUpPublished shouldBe true
//    }
})
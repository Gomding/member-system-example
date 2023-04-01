package com.member.service

import com.member.domain.MemberEventRepository
import com.member.domain.MemberEventType
import com.member.testfixture.MemberFixture
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.mockk
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component

@SpringBootTest
internal class MemberServiceTest(
    private val sut: MemberService,
    private val memberEventPublisherStub: MemberEventPublisherStub,
    private val memberEventRepository: MemberEventRepository,
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
        memberEventPublisherStub.published shouldBe  true
        val memberEvent = memberEventRepository.findByMemberId(signUpMemberId)
        memberEvent shouldNotBe null
        memberEvent!!.eventType shouldBe MemberEventType.SIGN_UP_EVENT
    }
})

@Primary
@Component
internal class MemberEventPublisherStub: EventPublisher(
    snsAsyncClient = mockk(),
    objectMapper = mockk(),
) {
    var published: Boolean = false

    override fun signUp(memberSignUpEvent: MemberSignUpEvent) {
        this.published = true
    }
}
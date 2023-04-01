package com.member.service

import com.member.domain.MemberEventRepository
import com.member.domain.MemberEventType
import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy
import io.awspring.cloud.messaging.listener.annotation.SqsListener
import org.slf4j.LoggerFactory
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class MemberSignUpEventListener(
    private val memberEventRepository: MemberEventRepository,
) {
    private val logger = LoggerFactory.getLogger(MemberSignUpEventListener::class.java)

    @SqsListener(
        value = ["member_sign_up_event_check_queue"],
        deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS,
    )
    fun memberSignUpEventDeliveryConfirmed(@Payload message: MemberSignUpEventPayload) {
        logger.info("member event received memberSignUpEventDeliveryConfirmed. message = $message")
        val memberEvent = memberEventRepository.findByMemberIdAndEventType(
            signUpMemberId = message.memberId,
            eventType = MemberEventType.SIGN_UP_EVENT,
        ) ?: throw IllegalArgumentException("회원 이벤트 발행 이력이 없습니다. memberId = ${message.memberId}")
        memberEvent.deliveryConfirmed()
        memberEventRepository.save(memberEvent)
    }
}

data class MemberSignUpEventPayload(
    val memberId: Long,
)
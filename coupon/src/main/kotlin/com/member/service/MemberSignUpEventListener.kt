package com.member.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy
import io.awspring.cloud.messaging.listener.annotation.SqsListener
import org.slf4j.LoggerFactory
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class CouponMemberSignedUpListener(
    private val couponService: CouponService,
    private val objectMapper: ObjectMapper = jacksonObjectMapper(),
) {
    private val logger = LoggerFactory.getLogger(CouponMemberSignedUpListener::class.java)

    @SqsListener(
        value = ["sign_up_coupon_publisher"],
        deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS,
    )
    @MessageMapping
    fun onMemberSignedUp(@Payload payload: Map<String, Any>) {
        logger.info("memberSignedUp message received")
        val message: String = payload["Message"] as String
        val memberSignedUpMessage = objectMapper.readValue(message, MemberSignedUpMessage::class.java)
        val memberId = memberSignedUpMessage.memberId
        couponService.signUpWelcomeCoupon(memberId)
        logger.info("memberSignedUp message received work success")
    }
}

data class MemberSignedUpMessage(
    val memberId: Long,
)
package com.member.service

import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy
import io.awspring.cloud.messaging.listener.annotation.SqsListener
import org.slf4j.LoggerFactory
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class CouponMemberSignedUpListener(
    private val couponService: CouponService,
) {
    private val logger = LoggerFactory.getLogger(CouponMemberSignedUpListener::class.java)

    @SqsListener(
        value = ["sign_up_coupon_publisher"],
        deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS,
    )
    @MessageMapping
    fun onMemberSignedUp(@Payload message: MemberSignedUpMessage) {
        logger.info("memberSignedUp message received")
        val memberId = message.memberId
        couponService.signUpWelcomeCoupon(memberId)
        logger.info("memberSignedUp message received work success")
    }
}

data class MemberSignedUpMessage(
    val memberId: Long,
)
package com.member.internal

import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class CouponClient {
    fun publishSignUpCoupon(memberId: Long) {
        val restTemplate = RestTemplate()
        restTemplate.postForLocation("http://localhost:8081/coupon/signUpCoupon", memberId)
    }
}
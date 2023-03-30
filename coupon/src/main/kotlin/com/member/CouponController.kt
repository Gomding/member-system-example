package com.member

import com.member.service.CouponService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/coupon")
class CouponController(
    private val couponService: CouponService,
) {

    @PostMapping("/signUpCoupon")
    fun publishSignUpCoupon(@RequestBody memberId: Long): ResponseEntity<Void> {
        val coupon = couponService.signUpWelcomeCoupon(memberId)
        return ResponseEntity.created(URI.create("/coupon/${coupon.id}")).build()
    }
}
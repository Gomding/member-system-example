package com.member.persistence

enum class CouponCode(
    val couponCode: String,
    val couponName: String,
) {
    SIGN_UP_WELCOME("S01", "신규가입 환영 쿠폰"),
}
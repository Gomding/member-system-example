package com.member.testfixture

import com.member.persistence.CouponType

object CouponTypeFixture {
    fun create(): CouponType {
        return CouponType(
            code = "testCode",
            name = "testName",
            discountAmount = 5000,
            validDays = 7,
        )
    }
}
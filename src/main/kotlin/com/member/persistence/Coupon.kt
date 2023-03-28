package com.member.persistence

import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Coupon(
    couponCode: String,
    validStartDate: LocalDate,
    validLastDate: LocalDate,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    var couponCode: String = couponCode
    protected set

    var validStartDate: LocalDate = validStartDate
    protected set

    var validLastDate: LocalDate = validLastDate
    protected set

    var memberId: Long = 0
    protected set

    fun issueCoupon(member: Member) {
        this.memberId = member.id
    }

    companion object {
        fun of(couponType: CouponType): Coupon {
            val validStartDate = LocalDate.now()
            return Coupon(
                couponCode = couponType.code,
                validStartDate = validStartDate,
                validLastDate = validStartDate.plusDays(couponType.validDays),
            )
        }
    }
}
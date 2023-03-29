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
    memberId: Long,
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

    var memberId: Long = memberId
    protected set

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Coupon

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    companion object {
        fun publishCoupon(couponType: CouponType, memberId: Long, validStartDate: LocalDate): Coupon {
            return Coupon(
                couponCode = couponType.code,
                validStartDate = validStartDate,
                validLastDate = validStartDate.plusDays(couponType.validDays),
                memberId = memberId,
            )
        }
    }
}
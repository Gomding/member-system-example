package com.member.persistence

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Coupon(
    couponCode: String,
    couponTypeId: Long,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    var couponCode: String = couponCode
    protected set

    var couponTypeId: Long = couponTypeId
    protected set
}
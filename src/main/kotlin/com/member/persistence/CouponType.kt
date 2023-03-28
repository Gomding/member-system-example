package com.member.persistence

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class CouponType(
    code: String,
    name: String,
    discountAmount: Int,
    validDays: Int,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    var code: String = code
    protected set

    var name: String = name
    protected set

    var discountAmount: Int = discountAmount
    protected set

    var validDays: Int = validDays
    protected set
}
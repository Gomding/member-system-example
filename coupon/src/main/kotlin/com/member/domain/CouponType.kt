package com.member.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class CouponType(
    code: String,
    name: String,
    discountAmount: Int,
    validDays: Long,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @Column(unique = true)
    var code: String = code
    protected set

    var name: String = name
    protected set

    var discountAmount: Int = discountAmount
    protected set

    var validDays: Long = validDays
    protected set

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CouponType

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }


}
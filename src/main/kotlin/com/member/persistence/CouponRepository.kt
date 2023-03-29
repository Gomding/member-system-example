package com.member.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface CouponRepository: JpaRepository<Coupon, Long> {
    fun findByMemberId(memberId: Long): Coupon?
}
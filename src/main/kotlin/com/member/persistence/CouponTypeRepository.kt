package com.member.persistence

import org.springframework.data.jpa.repository.JpaRepository

fun CouponTypeRepository.findCouponTypeByCode(code: String): CouponType {
    val couponType = this.findByCode(code) ?: throw IllegalArgumentException("존재하지 않는 쿠폰 유형 입니다. code = $code")
    return couponType
}

interface CouponTypeRepository: JpaRepository<CouponType, Long> {
    fun findByCode(code: String): CouponType?
}
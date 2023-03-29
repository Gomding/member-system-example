package com.member.service

import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service
import java.time.LocalDate

// 현재 시간을 구하는 Service 의 테스트용 Stub
@Service
@Primary
class CurrentDateTimeServiceStub(
    var isUsedLocalDateNow: Boolean = false,
): CurrentDateTimeService() {

    override fun localDateNow(): LocalDate {
        this.isUsedLocalDateNow = true
        return LocalDate.of(2023, 3, 7)
    }
}
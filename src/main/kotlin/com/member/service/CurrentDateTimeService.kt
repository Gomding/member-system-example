package com.member.service

import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class CurrentDateTimeService {
    fun localDateNow(): LocalDate {
        return LocalDate.now()
    }
}

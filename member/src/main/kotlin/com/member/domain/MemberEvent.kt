package com.member.domain

import java.time.LocalDateTime
import javax.persistence.*

@Entity
class MemberEvent(
    id: Long = 0,
    memberId: Long,
    eventType: MemberEventType,
    createdAt: LocalDateTime = LocalDateTime.now(),
    published: Boolean = false,
    publishedAt: LocalDateTime? = null
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = id
        protected set

    var memberId: Long = memberId
        protected set

    @Enumerated(EnumType.STRING)
    var eventType: MemberEventType = eventType
        protected set

    var createdAt: LocalDateTime = createdAt
        protected set

    var published: Boolean = published
        protected set

    var publishedAt: LocalDateTime? = publishedAt
        protected set

    fun deliveryConfirmed() {
        this.published = true
        this.publishedAt = LocalDateTime.now()
    }

    companion object {
        fun createSignUpEvent(memberId: Long): MemberEvent {
            return MemberEvent(
                memberId = memberId,
                eventType = MemberEventType.SIGN_UP_EVENT,
            )
        }
    }
}
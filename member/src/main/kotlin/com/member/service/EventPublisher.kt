package com.member.service

import com.amazonaws.services.sns.AmazonSNSAsync
import com.amazonaws.services.sns.model.MessageAttributeValue
import com.amazonaws.services.sns.model.PublishRequest
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component

@Component
class EventPublisher(
    private val snsAsyncClient: AmazonSNSAsync,
    private val objectMapper: ObjectMapper,
) {
    fun signUp(memberSignUpEvent: MemberSignUpEvent) {
        val publishRequest = PublishRequest()
            .withTopicArn(SIGN_UP_TOPIC_ARN)
            .withMessage(objectMapper.writeValueAsString(memberSignUpEvent))
        snsAsyncClient.publishAsync(publishRequest)
    }

    companion object {
        private const val SIGN_UP_TOPIC_ARN = "arn:aws:sns:ap-northeast-2:173306432695:member_sign_up"
    }
}
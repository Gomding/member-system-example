package com.member

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.sqs.AmazonSQSAsync
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder
import com.fasterxml.jackson.databind.ObjectMapper
import io.awspring.cloud.core.env.ResourceIdResolver
import io.awspring.cloud.messaging.config.SimpleMessageListenerContainerFactory
import io.awspring.cloud.messaging.core.QueueMessagingTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.messaging.converter.DefaultContentTypeResolver
import org.springframework.messaging.converter.MappingJackson2MessageConverter
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.util.MimeType
import java.nio.charset.StandardCharsets

@Configuration
class SqsConfig(
    private val objectMapper: ObjectMapper
) {
    @Value("\${cloud.aws.credentials.access-key}")
    lateinit var accessKey: String

    @Value("\${cloud.aws.credentials.secret-key}")
    lateinit var secretKey: String

    @Value("\${cloud.aws.region.static}")
    lateinit var region: String

    @Bean
    fun amazonSqs(): AmazonSQSAsync {
        val credential = BasicAWSCredentials(accessKey, secretKey)
        return AmazonSQSAsyncClientBuilder.standard()
            .withRegion(region)
            .withCredentials(AWSStaticCredentialsProvider(credential))
            .build()
    }

    @Primary
    @Bean
    fun awsMessageConverter(): MappingJackson2MessageConverter {
        val converter = MappingJackson2MessageConverter()
        converter.contentTypeResolver = createApplicationJsonDefaultContentTypeResolver()
        converter.objectMapper = objectMapper
        converter.serializedPayloadClass = String::class.java
        return converter
    }

    private fun createApplicationJsonDefaultContentTypeResolver(): DefaultContentTypeResolver {
        val defaultContentTypeResolver = DefaultContentTypeResolver()
        defaultContentTypeResolver.defaultMimeType = MimeType("application", "json", StandardCharsets.UTF_8)
        return defaultContentTypeResolver
    }

    @Primary
    @Bean
    fun queueMessagingTemplate(): QueueMessagingTemplate {
        return DestinationPreCacheableQueueMessagingTemplate(this.amazonSqs(), awsMessageConverter())
    }

    /**
     * 커스텀한 `SimpleMessageListenerContainer` 생성하기 위해 Factory를 빈으로 등록했다.
     */
    @Bean
    fun simpleMessageListenerContainerFactory(): SimpleMessageListenerContainerFactory {
        val factory = SimpleMessageListenerContainerFactory()
        factory.setAmazonSqs(this.amazonSqs())
        factory.setMaxNumberOfMessages(10) // Queue에서 한번 poll할때 가져올 최대 메시지 갯수
        if (0 > 0) {
            factory.setWaitTimeOut(20.coerceAtMost(0))
        }
        factory.setTaskExecutor(threadPoolTaskExecutor())
        return factory
    }

    @Bean
    fun threadPoolTaskExecutor(): ThreadPoolTaskExecutor {
        val executor = ThreadPoolTaskExecutor()
        executor.corePoolSize = 50
        executor.maxPoolSize = 50
        executor.setQueueCapacity(500)
        executor.setThreadNamePrefix("WORKER-LISTENER-")
        return executor
    }
}

class DestinationPreCacheableQueueMessagingTemplate(
    amazonSqs: AmazonSQSAsync,
    awsMessageConverter: MappingJackson2MessageConverter,
) : QueueMessagingTemplate(amazonSqs, null as ResourceIdResolver?, awsMessageConverter) {

    fun cachingMessageDestinationByLogicalName(destinationName: String) {
        resolveMessageChannelByLogicalName(destinationName)
    }
}
package com.member

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.sns.AmazonSNSAsync
import com.amazonaws.services.sns.AmazonSNSAsyncClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SnsConfig(
) {
    @Value("\${cloud.aws.credentials.access-key}")
    lateinit var accessKey: String

    @Value("\${cloud.aws.credentials.secret-key}")
    lateinit var secretKey: String

    @Value("\${cloud.aws.region.static}")
    lateinit var region: String

    @Bean
    fun amazonSns(): AmazonSNSAsync {
        val credential = BasicAWSCredentials(accessKey, secretKey)
        return AmazonSNSAsyncClientBuilder.standard()
            .withRegion(region)
            .withCredentials(AWSStaticCredentialsProvider(credential))
            .build()
    }
}
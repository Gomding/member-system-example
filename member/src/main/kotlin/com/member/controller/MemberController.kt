package com.member.controller

import com.member.controller.dto.MemberSignUpRequest
import com.member.service.MemberService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/member")
class MemberController(
    private val memberService: MemberService,
) {

    @PostMapping
    fun signUp(@RequestBody memberSignUpRequest: MemberSignUpRequest): ResponseEntity<Void> {
        val memberId = memberService.signUp(memberSignUpRequest.toEntity())
        return ResponseEntity.created(URI.create("/member/${memberId}")).build()
    }
}
package com.member.persistence

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "member")
@Entity
class Member(
    name: String,
    age: Int,
) {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @Column
    var name: String = name
    protected set

    @Column
    var age: Int = age
    protected set
}
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
    id: Long = 0,
    name: String,
    age: Int,
) {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = id

    @Column
    var name: String = name
    protected set

    @Column
    var age: Int = age
    protected set

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Member

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
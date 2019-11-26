package com.example.demo.domain

import lombok.EqualsAndHashCode
import lombok.ToString
import javax.persistence.*


@Entity
@Table(name = "bodyProfile")
@ToString(of = ["id", "account_id"])
@EqualsAndHashCode(of = ["id"])

class BodyProfile(
        @Column @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Int = 0,

        @Column(nullable = false, updatable = false)
        var account_id: Int = 0,

        @Column(nullable = true, updatable = true)
        public var chest: Float? = null,

        @Column(nullable = true, updatable = true)
        public var waist: Float? = null,

        @Column(nullable = true, updatable = true)
        var hips_girth: Float? = null,

        @Column(nullable = true, updatable = true)
        var sleeve: Float? = null,

        @Column(nullable = true, updatable = true)
        var height: Float? = null,

        @Column(nullable = true, updatable = true)
        var hip: Float? = null,

        @Column(nullable = true, updatable = true)
        var head: Float? = null
)



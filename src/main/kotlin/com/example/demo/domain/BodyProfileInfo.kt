package com.example.demo.domain

import lombok.EqualsAndHashCode
import lombok.ToString
import javax.persistence.*


@Entity
@Table(name = "bodyProfileInfo")
@ToString(of = ["id"])
@EqualsAndHashCode(of = ["id"])

class BodyProfileInfo(
        @Column @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Int = 0,

        @Column(nullable = false)
        var name: String = "",

        @Column(nullable = false)
        var translate: String = "",

        @Column(nullable = false)
        var description: String = "",

        @Column(nullable = false)
        var image: String = "",

        @Column(nullable = false)
        var minValue: Float = 0f,

        @Column(nullable = false)
        var maxValue: Float = 0f

//        @Column(columnDefinition = "json", updatable=true)
//        @Convert(converter = SuperMapJSONConverter::class)
//        var chest: BodyParameterInfo = BodyParameterInfo(),
//
//        @Column(columnDefinition = "json", updatable=true)
//        @Convert(converter = SuperMapJSONConverter::class)
//        var waist: BodyParameterInfo = BodyParameterInfo(),
//
//        @Column(columnDefinition = "json", updatable=true)
//        @Convert(converter = SuperMapJSONConverter::class)
//        var hips_girth: BodyParameterInfo = BodyParameterInfo(),
//
//        @Column(columnDefinition = "json", updatable=true)
//        @Convert(converter = SuperMapJSONConverter::class)
//        var sleeve: BodyParameterInfo = BodyParameterInfo(),
//
//        @Column(columnDefinition = "json", updatable=true)
//        @Convert(converter = SuperMapJSONConverter::class)
//        var height: BodyParameterInfo = BodyParameterInfo(),
//
//        @Column(columnDefinition = "json", updatable=true)
//        @Convert(converter = SuperMapJSONConverter::class)
//        var hip: BodyParameterInfo = BodyParameterInfo(),
//
//        @Column(columnDefinition = "json", updatable=true)
//        @Convert(converter = SuperMapJSONConverter::class)
//        var head: BodyParameterInfo = BodyParameterInfo()
)

data class BodyParameterInfo(
        var id: Int = 0,
        var name: String = "",
        var translate: String = "",
        var description: String = "",
        var image: String = "",
        var minValue: Float = 0f,
        var maxValue: Float = 0f
)

// chest
// waist
// hips_girth
// sleeve
// height
// hip
// head
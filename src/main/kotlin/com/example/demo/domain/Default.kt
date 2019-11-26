package com.example.demo.domain

import com.example.demo.MapJSONConverter
import com.mysql.cj.xdevapi.JsonArray
import lombok.EqualsAndHashCode
import lombok.ToString
import javax.persistence.*


@Entity
@Table(name = "defaults")
@ToString(of = ["id", "typeCode"])
@EqualsAndHashCode(of = ["id"])

class Default(
        @Column @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Int = 0,

        @Column(nullable = false, updatable = false)
        var typeCode: String = "",

        @Column(columnDefinition = "json")
        @Convert(converter = MapJSONConverter::class)
        var props: MutableMap<String, String> = mutableMapOf(),

        @Column(columnDefinition = "json")
        @Convert(converter = MapJSONConverter::class)
        var marks: MutableMap<String,  Any> = mutableMapOf(),

        @Column(columnDefinition = "json")
        @Convert(converter = MapJSONConverter::class)
        var params: MutableMap<String, Any> = mutableMapOf()
)



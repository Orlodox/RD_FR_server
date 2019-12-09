package com.example.demo.domain

import com.example.demo.MapJSONConverter
import com.mysql.cj.xdevapi.JsonArray
import lombok.EqualsAndHashCode
import lombok.ToString
import javax.persistence.*


@Entity
@Table
@ToString(of = ["id"])
@EqualsAndHashCode(of = ["id"])

class DefaultValue(
        @Column @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Int = 0,

        @ManyToOne(optional = false)
        @JoinColumn(name = "type")
        var type: Type = Type(),

        @Column(columnDefinition = "json")
        @Convert(converter = MapJSONConverter::class)
        var props: MutableMap<String, String> = mutableMapOf(),

        @Column(columnDefinition = "json")
        @Convert(converter = MapJSONConverter::class)
        var marks: MutableMap<String, Any> = mutableMapOf(),

        @Column(columnDefinition = "json")
        @Convert(converter = MapJSONConverter::class)
        var params: MutableMap<String, Any> = mutableMapOf(),

        @Column(columnDefinition = "json")
        @Convert(converter = MapJSONConverter::class)
        var sizes: MutableMap<String, Any> = mutableMapOf()
)



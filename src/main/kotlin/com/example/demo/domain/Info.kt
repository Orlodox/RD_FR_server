package com.example.demo.domain

import com.example.demo.MapJSONConverter
import javax.persistence.*

@Entity
@Table
class Info(

        @Column @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Int = 0,

        @Column(nullable = false, updatable = false, unique = true, name = "type_code")
        var typeCode: String = "",

        @Column(nullable = false, updatable = false, name = "type_name")
        var typeName: String = "",

        @Column(columnDefinition = "json") @Convert(converter = MapJSONConverter::class)
        var props: Map<String, Any> = mutableMapOf(),

        @Column(columnDefinition = "json", name = "size_columns") @Convert(converter = MapJSONConverter::class)
        var sizeColumns: Map<String, String> = mutableMapOf(),

        @Column(columnDefinition = "json") @Convert(converter = MapJSONConverter::class)
        var marks: Map<String, Any> = mutableMapOf(),

        @Column(columnDefinition = "json") @Convert(converter = MapJSONConverter::class)
        var params: Map<String, Any> = mutableMapOf()
)
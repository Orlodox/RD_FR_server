package com.example.demo.domain

import com.example.demo.MapJSONConverter
import lombok.EqualsAndHashCode
import lombok.ToString
import javax.persistence.*


//class Mark(
//        var delta: Int,
//        var mark: Int,
//        var comment: String
//)
//
//class Param(
//        var comment: String,
//        var limit: Double
//)

@Entity
@Table(name = "item")
@ToString(of = ["id"])
@EqualsAndHashCode(of = ["id"])

class Item(
        @Column @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Int = 0,

        @ManyToOne(optional = false)
        @JoinColumn(name = "type")
        var type: Type = Type(),

        @Column
        var name: String = "",

        @Column(columnDefinition = "json")
        @Convert(converter = MapJSONConverter::class)
        var props: MutableMap<String, String> = mutableMapOf(),

        @Column(columnDefinition = "json")
        @Convert(converter = MapJSONConverter::class)
        var sizes: MutableMap<String, Any> = mutableMapOf(),
//        var sizes: MutableMap<String, MutableMap<String, Int>> = mutableMapOf(),

        @Column(columnDefinition = "json")
        @Convert(converter = MapJSONConverter::class)
        var marks: MutableMap<String, Any> = mutableMapOf(),//ArrayList<Mark>> = mutableMapOf(),

        @Column(columnDefinition = "json")
        @Convert(converter = MapJSONConverter::class)
        var params: MutableMap<String, Any> = mutableMapOf()//ArrayList<Param>> = mutableMapOf()

)


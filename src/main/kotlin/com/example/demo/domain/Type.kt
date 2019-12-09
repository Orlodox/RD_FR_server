package com.example.demo.domain

import com.example.demo.MapJSONConverter
import com.fasterxml.jackson.annotation.JsonIgnore
import lombok.ToString
import javax.persistence.*

@Entity
@Table
@ToString(of = ["defaultValues", "items"])
class Type(

//        @Column @Id @GeneratedValue(strategy = GenerationType.AUTO)
        @Column @Id
//        var id: Int = 0,
        var typeCode: String = "",


//        @Column(nullable = false, updatable = false, unique = true, name = "type_code")
//        var typeCode: String = "",

        @Column(nullable = false, updatable = false, name = "type_name")
        var typeName: String = "",

        @Column(columnDefinition = "json") @Convert(converter = MapJSONConverter::class)
        var props: Map<String, Any> = mutableMapOf(),

        @Column(columnDefinition = "json", name = "sizes") @Convert(converter = MapJSONConverter::class)
        var sizes: Map<String, Any> = mutableMapOf(),

        @Column(columnDefinition = "json") @Convert(converter = MapJSONConverter::class)
        var marks: Map<String, Any> = mutableMapOf(),

        @Column(columnDefinition = "json") @Convert(converter = MapJSONConverter::class)
        var params: Map<String, Any> = mutableMapOf(),

        @JsonIgnore
        @OneToMany(mappedBy = "type", fetch = FetchType.LAZY, cascade = [CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST])
        var defaultValues: Collection<DefaultValue> = emptyList(),

        @JsonIgnore
        @OneToMany(mappedBy = "type", fetch = FetchType.LAZY, cascade = [CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST])
        var items: Collection<Item> = emptyList()

)
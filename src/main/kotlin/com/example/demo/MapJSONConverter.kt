package com.example.demo

import java.io.IOException
import com.fasterxml.jackson.core.JsonProcessingException
import javax.persistence.AttributeConverter
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

//class MapJSONConverter : AttributeConverter<MutableMap<String, Any>, String> {
//
//    override fun convertToDatabaseColumn(objectAsMap: MutableMap<String, Any>): String {
//
//        val objectAsJSON: String
//
//        try {
//            objectAsJSON = jacksonObjectMapper().writeValueAsString(objectAsMap)
//        }
//        catch (e: JsonProcessingException) {
//            throw Exception("JSON writing error: $e")
//        }
//
//        return objectAsJSON
//    }
//
//    override fun convertToEntityAttribute(objectAsJSON: String): MutableMap<String, Any> {
//
//        var objectAsMap: MutableMap<String, Any> = mutableMapOf()
//
//
//        try {
//            objectAsMap = jacksonObjectMapper().readValue(objectAsJSON, objectAsMap.javaClass)
//        }
//        catch (e: IOException) {
//            throw Exception("JSON reading error: $e")
//        }
//
//        return objectAsMap
//    }
//}

class MapJSONConverter : AttributeConverter<Any, String> {

    override fun convertToDatabaseColumn(objectAsMap: Any): String {

        val objectAsJSON: String

        try {
            objectAsJSON = jacksonObjectMapper().writeValueAsString(objectAsMap)
        }
        catch (e: JsonProcessingException) {
            throw Exception("JSON writing error: $e")
        }

        return objectAsJSON
    }

    override fun convertToEntityAttribute(objectAsJSON: String): Any {

        var objectAsAny = Any()


        try {
            objectAsAny = jacksonObjectMapper().readValue(objectAsJSON, objectAsAny.javaClass)
        }
        catch (e: IOException) {
            throw Exception("JSON reading error: $e")
        }

        return objectAsAny
    }
}
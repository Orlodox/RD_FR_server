package com.example.demo.controllers

import com.example.demo.domain.Type
import com.example.demo.repo.TypeRepo
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping
class TypeController(var typeRepo: TypeRepo) {

    @GetMapping("typeList")
    fun getTypeList(): Map<String, String> {
        return typeRepo.findAll().map { it.typeCode to it.typeName }.toMap()
    }

    @GetMapping("type/{typeCode}")
    fun getTypeInfo(@PathVariable("typeCode") typeCode: String): Any {
        return typeRepo.findById(typeCode)
    }


    @PostMapping("type")
    fun createType(@RequestBody newType: Type) {
        typeRepo.save(newType)
    }
}
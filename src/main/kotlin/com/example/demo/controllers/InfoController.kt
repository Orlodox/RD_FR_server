package com.example.demo.controllers

import com.example.demo.domain.Info
import com.example.demo.repo.InfoRepo
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping
class InfoController(var infoRepo: InfoRepo) {

    @GetMapping("typeList")
    fun getTypeList(): Map<String, String> {
        return infoRepo.findAll().map { it.typeCode to it.typeName }.toMap()
    }

    @GetMapping("type/{typeCode}")
    fun getTypeInfo(@PathVariable("typeCode") typeCode: String): Any {
        return infoRepo.findByTypeCode(typeCode)
    }


    @PostMapping("addType")
    fun initInfo(@RequestBody info: Info) {
        infoRepo.save(info)
    }
}
package com.example.demo.controllers

import com.example.demo.domain.BodyParameterInfo
import com.example.demo.domain.BodyProfileInfo
import com.example.demo.repo.BodyProfileInfoRepo
import org.springframework.beans.BeanUtils
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping
class BodyProfileInfoController(var bodyProfileInfoRepo: BodyProfileInfoRepo) {

    @GetMapping("bodyProfileInfo")
    fun getBodyProfileInfo(): List<BodyProfileInfo> {
        var a = bodyProfileInfoRepo.findAll()
//        val a = bodyProfileInfoRepo.findAll()
//        val b = a.first()
        return a//bodyProfileInfoRepo.findAll().first()
    }

    @PutMapping("bodyProfileInfo/{id}")
    fun updateBodyProfileInfo(@PathVariable("id") bodyProfileInfoFromDB: BodyProfileInfo, @RequestBody newBodyProfileInfo: BodyProfileInfo): Int {
        try {
            BeanUtils.copyProperties(newBodyProfileInfo, bodyProfileInfoFromDB, "id")
            bodyProfileInfoRepo.save(bodyProfileInfoFromDB)
        }
        catch (e: Throwable) {
            return -1   // errors
        }
        return 1        // success
    }

    @PostMapping("bodyProfileInfo")
    fun createBodyProfileInfo(@RequestBody newBodyProfileInfo: BodyProfileInfo): BodyProfileInfo {
        return bodyProfileInfoRepo.save(newBodyProfileInfo)
    }
}
package com.example.demo.controllers

import com.example.demo.domain.Default
import com.example.demo.repo.DefaultRepo
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping
class DefaultController(var defaultRepo: DefaultRepo) {

    @PostMapping("createDefault")
    fun createDefault(@RequestBody defaultValues: Default) = defaultRepo.save(defaultValues)
}
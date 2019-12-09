package com.example.demo.controllers

import com.example.demo.domain.DefaultValue
import com.example.demo.domain.Type
import com.example.demo.repo.DefaultValueRepo
import com.example.demo.repo.TypeRepo
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping
class DefaultController(var defaultValueRepo: DefaultValueRepo, var typeRepo: TypeRepo) {

    @PostMapping("createDefault")
    fun createDefault(@RequestBody defaultValues: DefaultValueAPI): DefaultValue {
        val default = DefaultValue(props = defaultValues.props, marks = defaultValues.marks, params = defaultValues.params, sizes = defaultValues.sizes)
        default.type = typeRepo.findByIdOrNull(defaultValues.typeCode) ?: Type()
        return defaultValueRepo.save(default)
    }
}

class DefaultValueAPI(
        val typeCode: String,
        val props: MutableMap<String, String>,
        val marks: MutableMap<String, Any>,
        val params: MutableMap<String, Any>,
        val sizes: MutableMap<String, Any>
)

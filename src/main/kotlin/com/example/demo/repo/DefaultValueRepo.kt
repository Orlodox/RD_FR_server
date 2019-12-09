package com.example.demo.repo

import com.example.demo.domain.DefaultValue
import org.springframework.data.jpa.repository.JpaRepository


interface DefaultValueRepo : JpaRepository<DefaultValue, Int> {

//    @Query("SELECT d FROM DefaultValue d WHERE d.type = ?1")
//    fun findByType(type: String): DefaultValue?
}
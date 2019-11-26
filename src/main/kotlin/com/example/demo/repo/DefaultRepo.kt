package com.example.demo.repo

import com.example.demo.domain.Default
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query


interface DefaultRepo : JpaRepository<Default, Int> {

    @Query("SELECT d FROM Default d WHERE d.typeCode = ?1")
    fun findByTypeCode(typeCode: String): Default
}
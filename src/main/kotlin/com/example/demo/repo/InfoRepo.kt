package com.example.demo.repo

import com.example.demo.domain.Info
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query


interface InfoRepo : JpaRepository<Info, Int> {

    @Query("SELECT t FROM Info t WHERE t.typeCode = ?1")
    fun findByTypeCode(typeCode: String): Info
}

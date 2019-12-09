package com.example.demo.repo

import com.example.demo.domain.Type
import org.springframework.data.jpa.repository.JpaRepository


interface TypeRepo : JpaRepository<Type, String> {

//    @Query("SELECT t FROM Type t WHERE t.typeCode = ?1")
//    fun findByTypeCode(typeCode: String): Type
}

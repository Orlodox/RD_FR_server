package com.example.demo.repo

import com.example.demo.domain.BodyProfile
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query


interface BodyProfileRepo : JpaRepository<BodyProfile, Int> {

    @Query("SELECT bp FROM BodyProfile bp WHERE bp.account_id = ?1")
    fun findByAccountId(account_id: Int): BodyProfile?
}
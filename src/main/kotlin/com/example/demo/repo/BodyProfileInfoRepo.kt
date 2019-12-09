package com.example.demo.repo

import com.example.demo.domain.BodyProfileInfo
import org.springframework.data.jpa.repository.JpaRepository


interface BodyProfileInfoRepo : JpaRepository<BodyProfileInfo, Int>
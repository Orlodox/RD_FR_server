package com.example.demo.repo

import com.example.demo.ClothItem
import org.springframework.data.repository.CrudRepository

interface ClothItemRepo : CrudRepository<ClothItem, Long> {
}
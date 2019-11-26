package com.example.demo

import javax.persistence.Entity
import javax.persistence.GenerationType
import javax.persistence.GeneratedValue
import javax.persistence.Id


@Entity
class ClothItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int? = null

    var typeName: String = "Test"
    var properties: ArrayList<Pair<String, String>> = arrayListOf()
    var sizes: ArrayList<Size> = arrayListOf()
    var marksList: String = "dfs"
}
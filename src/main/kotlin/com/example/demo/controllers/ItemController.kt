package com.example.demo.controllers

import com.example.demo.domain.Item
import com.example.demo.repo.DefaultRepo
import com.example.demo.repo.ItemRepo
import org.springframework.beans.BeanUtils
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping
class ItemController(var itemRepo: ItemRepo, var defaultRepo: DefaultRepo) {

    @GetMapping("getAll")
    fun getAll(): List<Item> = itemRepo.findAll()

    @GetMapping("getItemList")
    fun getItemList(): List<Any> = itemRepo.findAll().map {
        object {
            var id = it.id
            var name = it.name
            var typeName = it.typeName
        }
    }

    @GetMapping("item/{id}")
    fun getItemById(@PathVariable("id") item: Item) = item

    @PostMapping("create")
    fun createItem(@RequestBody item: Item): Item {
        val defaults = defaultRepo.findByTypeCode(item.typeCode)
        item.params = defaults.params
        item.marks = defaults.marks
        item.props = defaults.props
        return itemRepo.save(item)
    }

    @PutMapping("update/{id}")
    fun updateItem(@PathVariable("id") itemFromDB: Item, @RequestBody item: Item): Int {
        try {
            BeanUtils.copyProperties(item, itemFromDB, "id", "typeCode", "typeName")
            itemRepo.save(itemFromDB)
        }
        catch (e: Throwable) {
            return -1   // errors
        }
        return 1        // success
    }

    @DeleteMapping("delete/{id}")
    fun deleteItem(@PathVariable("id") item: Item) {
        itemRepo.delete(item)
    }

    @GetMapping("getProps/{id}")
    fun getProps(@PathVariable("id") item: Item) = item.props


//    @PostMapping("/addProduct")
//    fun addProduct(@PathVariable("type") type: String): ResponseEntity<Map<String, Map<String, Int>>> {
//        val headers = HttpHeaders()
//        headers.set("Access-Control-Allow-Origin", "http://localhost:3000")
//        return ResponseEntity.ok()
//                .headers(headers)
//                .body(Size().size)
//    }
//    @GetMapping("/{name}")
//    fun getName(@PathVariable("name") name: String): ResponseEntity<String> {
//        val headers = HttpHeaders()
//        headers.set("Access-Control-Allow-Origin", "http://localhost:3000")
//        return ResponseEntity.ok()
//                .headers(headers)
//                .body("Name: $name\n")
//    }
//    @GetMapping("/daitablitsy")
//    fun sendTable(): ResponseEntity<Array<String>> {
//        val headers = HttpHeaders()
//        headers.set("Access-Control-Allow-Origin", "http://localhost:3000")
//        return ResponseEntity.ok()
//                .headers(headers)
//                .body(MarksForTshirt().generalMarksForTshirt)
//    }

}

package com.example.demo.controllers

import com.example.demo.domain.Item
import com.example.demo.domain.Type
import com.example.demo.repo.DefaultValueRepo
import com.example.demo.repo.ItemRepo
import com.example.demo.repo.TypeRepo
import org.springframework.beans.BeanUtils
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping
class ItemController(var itemRepo: ItemRepo, var defaultValueRepo: DefaultValueRepo, var typeRepo: TypeRepo) {

//    @GetMapping("getAll")
//    fun getAll(): List<Item> = itemRepo.findAll()

    @GetMapping("itemList")
    fun getItemList(): List<Any> = itemRepo.findAll().map {
        object {
            val id = it.id
            val name = it.name
            val typeName = it.type.typeName
        }
    }

    @GetMapping("item/{id}")
    fun getItemById(@PathVariable("id") item: Item) = item

    @PostMapping("create")
    fun createItem(@RequestBody itemInfo: ItemInfoAPI): Item {
        val item = Item(name = itemInfo.name, sizes = itemInfo.sizes)
        item.type = typeRepo.findByIdOrNull(itemInfo.typeCode) ?: Type()
        val defaultValue = item.type.defaultValues.firstOrNull()//defaultValueRepo.findByTypeCode(item.type)
        if (defaultValue != null) {
            item.params = defaultValue.params
            item.marks = defaultValue.marks
            item.props = defaultValue.props
            if (item.sizes.isEmpty()) item.sizes = defaultValue.sizes
        }

        return itemRepo.save(item)
    }

    @PutMapping("update/{id}")
    fun updateItem(@PathVariable("id") itemFromDB: Item, @RequestBody item: Item): Int {
        try {
            BeanUtils.copyProperties(item, itemFromDB, "id", "type")
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

    @PostMapping("auth")
    fun authCheck(@RequestBody authData: AuthData): AuthResponse {
        return if (authData.login == "admin" && authData.password == "123")
            AuthResponse(0, 777)
        else AuthResponse(-1, null)
    }


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

class ItemInfoAPI(
        val name: String,
        val typeCode: String,
        val sizes: MutableMap<String, Any>
)

class AuthData(
        val login: String,
        val password: String
)

class AuthResponse(
        val resultCode: Int,
        val userId: Int?
)
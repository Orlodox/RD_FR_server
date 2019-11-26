package com.example.demo.controllers

import com.example.demo.domain.BodyProfile
import com.example.demo.domain.Info
import com.example.demo.domain.Item
import com.example.demo.repo.BodyProfileRepo
import com.example.demo.repo.InfoRepo
import com.example.demo.repo.ItemRepo
import org.springframework.beans.BeanUtils
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("widget")
class WidgetController(var bodyProfileRepo: BodyProfileRepo, var itemRepo: ItemRepo, var infoRepo: InfoRepo) {

    @GetMapping("bodyProfile/{account_id}")
    fun getBodyProfileByAccountId(@PathVariable("account_id") accountID: Int): BodyProfile? {
        var bodyProfile = bodyProfileRepo.findByAccountId(accountID)
        if (bodyProfile == null) {
            bodyProfile = BodyProfile(account_id = accountID)
            bodyProfileRepo.save(bodyProfile)
        }
        return bodyProfile
    }

    @PutMapping("bodyProfile/{account_id}")
    fun updateBodyProfile(@PathVariable("account_id") accountID: Int, @RequestBody newBodyProfile: BodyProfile): Int {
        var bodyProfileFromDB = bodyProfileRepo.findByAccountId(accountID)
        if (bodyProfileFromDB == null) bodyProfileFromDB = BodyProfile(account_id = accountID)
        try {
            BeanUtils.copyProperties(newBodyProfile, bodyProfileFromDB, "id", "account_id")
            bodyProfileRepo.save(bodyProfileFromDB)
        }
        catch (e: Throwable) {
            return -1   // errors
        }
        return 1        // success
    }

    @GetMapping("fitting")
    fun getFittingResults(@RequestParam itemID: Int, @RequestParam customerID: Int): Any {
        val item = itemRepo.findByIdOrNull(itemID)
        val customer = bodyProfileRepo.findByAccountId(customerID)
        if (item == null || customer == null)
            return FittingResults(itemID, customerID, "Изделие или пользователь не найдены")
        return FittingInfo(item, infoRepo.findByTypeCode(item.typeCode), customer).results()
    }
}

class FittingInfo(val item: Item, val typeInfo: Info, val customer: BodyProfile) {

    private fun sizeResult(sizeName: String, sizeValues: MutableMap<String, Int>) {
        val estimatedParams = item.marks["sda"] as Mark
    }

    private fun informationalParametersResult() {
        val params = typeInfo.params as ParamsInfo
        val informationalParams = item.params.map { param ->
            object {
                val title = params.tabs[param.key]
                val comment = item.params[param.key] as ArrayList<Param>
            }
        }

    }

    fun results() {
//        val marks =
//                mutableMapOf<String, SizeInfo>(*item.sizes.map { size -> size.key to sizeResult(size.key, size.value) }.toTypedArray())
    }
}

data class FittingResults(
        val item_id: Int,
        val customer_id: Int,
        val errorMessage: String? = null,
        val unavailableBodyProfileFields: ArrayList<String>? = null,
        val marks: MutableMap<String, SizeInfo> = mutableMapOf())

data class SizeInfo(
        val main: EstimatedParameter,
        val estimatedParams: ArrayList<EstimatedParameter>,
        val informationalParams: ArrayList<InformationalParameter>) {

    data class EstimatedParameter(
            val title: String,
            val comment: String,
            val mark: Int)

    data class InformationalParameter(
            val title: String,
            val comment: String)
}

data class Mark(
        var delta: Int,
        var mark: Int,
        var comment: String
)

data class ParamsInfo(
        val tabs: MutableMap<String, String>,
        val columns: MutableMap<String, String>
)

data class Param(
        val limit: Float,
        val comment: String
)

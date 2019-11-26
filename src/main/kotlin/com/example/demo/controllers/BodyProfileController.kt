package com.example.demo.controllers

import com.example.demo.domain.BodyProfile
import com.example.demo.repo.BodyProfileRepo
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping
class BodyProfileController(var bodyProfileRepo: BodyProfileRepo) {

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
    fun updateBodyProfile(@PathVariable("account_id") accountID: Int,
                          @RequestParam("name") paramName: String,
                          @RequestParam("value") paramValue: Float): Int {
        val bodyProfileFromDB = bodyProfileRepo.findByAccountId(accountID)
        if (bodyProfileFromDB != null && paramName != "id") //bodyProfileFromDB = BodyProfile(account_id = accountID)
            try {
                val paramField = bodyProfileFromDB.javaClass.getDeclaredField(paramName)
                paramField.isAccessible = true
                paramField.set(bodyProfileFromDB, if (paramValue == 0f) null else paramValue)
//            BeanUtils.copyProperties(newBodyProfile, bodyProfileFromDB, "id", "account_id")
                bodyProfileRepo.save(bodyProfileFromDB)
            }
            catch (e: Throwable) {
                return -1   // errors
            }
        return 1        // success
    }

    @DeleteMapping("bodyProfile/{account_id}")
    fun deleteBodyProfile(@PathVariable("account_id") accountID: Int) {
        val bodyProfile = bodyProfileRepo.findByAccountId(accountID)
        if (bodyProfile != null) bodyProfileRepo.delete(bodyProfile)
    }

}
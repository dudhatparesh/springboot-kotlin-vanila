package com.enggdream.demo.controller

import com.enggdream.demo.dto.ResponseDTO
import com.enggdream.demo.entity.Item
import com.enggdream.demo.service.ItemService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.InputStreamResource
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.nio.file.Files

@Controller
class ItemController {

    @GetMapping("/")
    fun indexPage(): String {
        return "index"
    }

    @Autowired
    lateinit var itemService: ItemService

    @GetMapping("/items")
    @ResponseBody
    fun getItems(): ResponseDTO {
        return itemService.getItems()
    }

    @PostMapping("/item/add")
    @ResponseBody
    fun addItem(@RequestParam("name") name: String,
                @RequestParam("description") description: String,
                @RequestParam("latitude") latitude: Double,
                @RequestParam("longitude") longitude: Double,
                @RequestParam("currency") currency: String,
                @RequestParam("amount") amount: Double,
                @RequestPart("file") imageFile: MultipartFile
    ): ResponseDTO {

        return itemService.saveItem(name, description,latitude,longitude,currency,amount,imageFile)
    }

    @PostMapping("/item/update")
    @ResponseBody
    fun update(@RequestParam("name") name: String,
                @RequestParam("description") description: String,
                @RequestParam("latitude") latitude: Double,
                @RequestParam("longitude") longitude: Double,
                @RequestParam("currency") currency: String,
                @RequestParam("amount") amount: Double,
                @RequestPart("file") imageFile: MultipartFile,
                @RequestParam("id") itemId: Int
    ): ResponseDTO {

        return itemService.saveItem(name, description,latitude,longitude,currency,amount,imageFile,itemId)

    }
    @GetMapping("/images/{imageName}")
    @ResponseBody
    fun getImage(@PathVariable("imageName") imageName: String): ResponseEntity<InputStreamResource> {
        return itemService.getImage(imageName)
    }

    @PostMapping("/item/delete")
    @ResponseBody
    fun deleteItem(@RequestParam("id") itemId: Int):ResponseDTO{
        return itemService.deleteItem(itemId)
    }
}
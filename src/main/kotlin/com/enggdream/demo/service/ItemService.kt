package com.enggdream.demo.service

import com.enggdream.demo.dto.ResponseDTO
import com.enggdream.demo.entity.Item
import com.enggdream.demo.repositories.ItemRepository
import jdk.nashorn.internal.runtime.options.Option
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.InputStreamResource
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileOutputStream
import java.nio.file.Files
import java.util.*

@Service
class ItemService{


    @Value("\${images.storageDir}")
    private val imageStorageDir: String? = null

    @Autowired
    lateinit var itemRepository: ItemRepository

    fun saveItem(name: String, description: String, latitude: Double, longitude: Double, currency: String,
                 amount: Double, imageFile: MultipartFile, id: Int? = null): ResponseDTO{

        val item : Item
        item = if(id!=null){
            val itemResult:Optional<Item> = itemRepository.findById(id)
            if(itemResult.isPresent){
                itemResult.get()
            }else{
                return ResponseDTO(500,"Item not found",null)
            }
        }else{
            Item()
        }
        val file = File(imageStorageDir)

        if (!file.isDirectory || !file.exists()) {
            file.mkdir()
        }
        val imageName = System.currentTimeMillis().toString() + "." + imageFile.originalFilename.toString().substring(
                imageFile.originalFilename.toString().lastIndexOf(".") + 1)
        val imageFileToSave = File(file, imageName)
        imageFileToSave.createNewFile()
        val fileOutputStream = FileOutputStream(imageFileToSave)
        fileOutputStream.write(imageFile.bytes)
        fileOutputStream.close()

        item.name= name
        item.description = description
        item.latitude = latitude
        item.longitude = longitude
        item.amount = amount
        item.currency = currency
        item.imageName = imageName
        item.contentType = imageFile.contentType
        itemRepository.save(item)

        return  ResponseDTO(200,"Item Saved", item)
    }

    fun getItems(): ResponseDTO {
        return ResponseDTO(200,"",itemRepository.findAll())
    }

    fun getImage(imageName: String): ResponseEntity<InputStreamResource> {
        val file = File(imageStorageDir,imageName)
        var  contentType = Files.probeContentType(file.toPath())
        if(contentType==null){
            val items= itemRepository.findByImageName(imageName)
            if(items.isNotEmpty()) {
                contentType = items[0].contentType
            }
        }
        return ResponseEntity.ok()
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType(contentType))
                .body( InputStreamResource(file.inputStream()))
    }

    fun deleteItem(itemId: Int): ResponseDTO {
        itemRepository.deleteById(itemId)
        return ResponseDTO(200,"Item Deleted")
    }
}
package com.enggdream.demo.repositories

import com.enggdream.demo.entity.Item
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ItemRepository : CrudRepository<Item, Int>{
    fun findByImageName(imageName: String):List<Item>
}
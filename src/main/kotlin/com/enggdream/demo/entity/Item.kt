package com.enggdream.demo.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Item(
        @Id
        @GeneratedValue
        var id: Int? =null,

        @Column(name = "name")
        var name: String? = null,

        @Column(name= "description")
        var description: String? = null,

        @Column(name ="image_name")
        var imageName: String? = null,

        @Column(name = "latitude")
        var latitude: Double? = null,

        @Column(name = "longitude")
        var longitude: Double? = null,

        @Column(name = "amount")
        var amount: Double? = null,

        @Column(name = "currency")
        var currency: String? = null,

        @Column(name = "content_type")
        var contentType : String? = null
)
package com.enggdream.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MainApplication
fun main(args: Array<String>) {
    runApplication<MainApplication>(*args)
}
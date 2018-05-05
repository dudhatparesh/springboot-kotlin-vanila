package com.enggdream.test.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class DemoController {
    @GetMapping("/")
    fun indexPage(): String {
        return "index"
    }
}
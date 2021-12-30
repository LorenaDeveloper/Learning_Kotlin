package com.KotlinProject.product.Controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController //to identify this class as controller
@RequestMapping("/test") //to select endpoint path
class HelloController {
    @GetMapping  // uses main endpoint when GET invoked
    fun hello() = "Hello World" //public function returning string
}
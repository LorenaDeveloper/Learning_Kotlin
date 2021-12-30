package com.KotlinProject.product.Controller

import com.KotlinProject.product.Service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/product")
class ProductController(@Autowired private val productService: ProductService) { // dependency injection by constructor

    @GetMapping("/get")
    fun findAll() = productService.findAll()
}

//another way to do it
//@RestController
//@RequestMapping("/api/v1/product")
//class ProductController {
//
//    @Autowired
//    private lateinit var productService: ProductService // 'lateinit' indicates that it'll be initialized later
//
//    @GetMapping("/get")
//    fun findAll() = productService.findAll()
//}



package com.KotlinProject.product.Controller

import com.KotlinProject.product.Service.ProductService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/product")
class ProductController(private val productService: ProductService) {
    @GetMapping("/get")
    fun findAll() = productService.findAll()
}
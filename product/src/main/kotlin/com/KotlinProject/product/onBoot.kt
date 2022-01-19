package com.KotlinProject.product

import com.KotlinProject.product.Model.Product
import com.KotlinProject.product.Service.ProductService
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class onBoot(private val productService: ProductService):ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        listOf(Product("apple", 5.0, 10), Product("orange", 3.0, 15)).forEach {
            println("Saving -> ${it.name}")
            productService.save(it)
        }
    }
}
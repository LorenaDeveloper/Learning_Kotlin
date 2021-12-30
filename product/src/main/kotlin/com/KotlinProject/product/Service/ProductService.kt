package com.KotlinProject.product.Service

import com.KotlinProject.product.Model.Product
import org.springframework.stereotype.Service

@Service
class ProductService {
    private val products:Set<Product> = setOf(Product("Apple", 22.2), Product(price = 33.3, name = "Banana"))

    //function than returns set of products as list
    fun findAll():List<Product> = products.toList();
}
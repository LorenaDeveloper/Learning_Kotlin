package com.KotlinProject.product.Service

import com.KotlinProject.product.Interface.BasicCrud
import com.KotlinProject.product.Model.Product
import org.springframework.stereotype.Service

@Service
class ProductService:BasicCrud<Product, String> {       // implements the interface
    private val products:Set<Product> = setOf(Product("Apple", 22.2), Product(price = 33.3, name = "Banana"))

    //function than returns set of products as list
    override fun findAll():List<Product> = products.toList();

    override fun findById(id: String): Product? {
        TODO("Not yet implemented")
    }

    override fun save(t: Product): Boolean {
        TODO("Not yet implemented")
    }

    override fun update(t: Product): Boolean {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: String): Boolean {
        TODO("Not yet implemented")
    }
}
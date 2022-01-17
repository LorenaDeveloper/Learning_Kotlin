package com.KotlinProject.product.Service

import com.KotlinProject.product.Interface.BasicCrud
import com.KotlinProject.product.Model.Product
import org.springframework.stereotype.Service

@Service
class ProductService:BasicCrud<Product, String> {       // implements interface BasicCrud
    //to create mutable set
    private val products:MutableSet<Product> =  mutableSetOf(Product("Apple", 22.2), Product(price = 33.3, name = "Banana"))

    //function than returns set of products as list
    override fun findAll():List<Product> = products.toList();

    override fun findById(id: String): Product? {
        return this.products.find { it.name == id } //lambda function. find the product whose name equals string 'id'.
                                                    // uses 'it' as iterator; another way -> { product -> product.name == id }
    }

    override fun save(t: Product): Boolean {
        val found = findAll().filter { product -> product.name == t.name }
        println(found)
        if (found.isNotEmpty()) return false
        return this.products.add(t)
    }

    override fun update(t: Product): Boolean {
        return this.deleteById(t.name) && this.products.add(t)
    }

    override fun deleteById(id: String): Boolean {
        return this.products.remove(this.findById(id))
    }
}
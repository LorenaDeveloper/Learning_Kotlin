package com.KotlinProject.product.Service

import com.KotlinProject.product.Dto.ProductDTO
import com.KotlinProject.product.Interface.BasicCrud
import com.KotlinProject.product.Model.Product
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ProductService(private val productDTO: ProductDTO):BasicCrud<Product, String> {       // implements interface BasicCrud

    //function than returns set of products as list
    override fun findAll():List<Product> = this.productDTO.findAll();

    override fun findById(id: String): Product? {
        return this.productDTO.findByIdOrNull(id)
    }

    override fun save(t: Product): Boolean {
//        val found = findAll().filter { product -> product.name == t.name }
//        if (found.isNotEmpty()) return false
        this.productDTO.save(t).let { return true }
    }

    override fun update(t: Product): Boolean {
        this.productDTO.save(t).let { return true }
    }

    override fun deleteById(id: String): Boolean {
        this.productDTO.deleteById(id).let { return true }
    }
}
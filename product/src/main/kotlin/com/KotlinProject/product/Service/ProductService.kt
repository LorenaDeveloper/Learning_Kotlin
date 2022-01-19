package com.KotlinProject.product.Service

import com.KotlinProject.product.Dao.ProductDAO
import com.KotlinProject.product.Interface.BasicCrud
import com.KotlinProject.product.Model.Product
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class ProductService(private val productDAO: ProductDAO):BasicCrud<Product, String> {       // implements interface BasicCrud

    //function than returns set of products as list
    override fun findAll():List<Product> = this.productDAO.findAll();

    override fun findById(id: String): Product? {
        return this.productDAO.findByIdOrNull(id)
    }

    override fun save(t: Product): Product {
        return if(!this.productDAO.existsById(t.name)) this.productDAO.save(t) else throw org.springframework.dao.DuplicateKeyException("${t.name} already exists")
    }

    override fun update(t: Product): Product {
        return if(this.productDAO.existsById(t.name)) this.productDAO.save(t) else throw EntityNotFoundException("${t.name} doesn't exists")
    }

    override fun deleteById(id: String): Product {
        return this.findById(id)?.apply {
            this@ProductService.productDAO.deleteById(id)
        } ?: throw EntityNotFoundException("$id doesn't exists")
    }
}
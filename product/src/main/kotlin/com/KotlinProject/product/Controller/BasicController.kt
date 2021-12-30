package com.KotlinProject.product.Controller

import com.KotlinProject.product.Interface.BasicCrud
import org.springframework.web.bind.annotation.*

abstract class BasicController<T, ID> (private val basicCrud: BasicCrud<T, ID> ){
    @GetMapping("/")
    fun findAll() = basicCrud.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id:ID) = basicCrud.findById(id)

    @PostMapping
    fun addProduct(@RequestBody body: T) = basicCrud.save(body)

    @PutMapping
    fun updateProduct(@RequestBody body: T) = basicCrud.update(body)

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id:ID) = basicCrud.deleteById(id)
}
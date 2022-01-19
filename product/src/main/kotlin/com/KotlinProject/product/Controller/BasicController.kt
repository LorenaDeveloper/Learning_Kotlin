package com.KotlinProject.product.Controller

import com.KotlinProject.product.Interface.BasicCrud
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

abstract class BasicController<T, ID> (private val basicCrud: BasicCrud<T, ID> ){

    @ApiOperation("Get all entities")
    @GetMapping("/")
    fun findAll() = basicCrud.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id:ID): ResponseEntity<T> {
        val entity = basicCrud.findById(id)
        return ResponseEntity.status(if (entity!= null) HttpStatus.OK else HttpStatus.NOT_FOUND).body(entity)
    }

    @PostMapping
    fun addProduct(@Valid @RequestBody body: T) = this.basicCrud.save(body)

    @PutMapping
    fun updateProduct(@Valid @RequestBody body: T) = this.basicCrud.update(body)

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id:ID) = this.basicCrud.deleteById(id)
}
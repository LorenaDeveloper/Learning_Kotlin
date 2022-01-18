package com.KotlinProject.product.Controller

import com.KotlinProject.product.Interface.BasicCrud
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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
    fun addProduct(@RequestBody body: T): ResponseEntity<Boolean> {
        val entity = basicCrud.save(body)
        return ResponseEntity.status(if (entity) HttpStatus.CREATED else HttpStatus.CONFLICT).body(entity)
    }

    @PutMapping
    fun updateProduct(@RequestBody body: T): ResponseEntity<Boolean> {
        val entity = basicCrud.update(body)
        return ResponseEntity.status(if (entity) HttpStatus.OK else HttpStatus.NOT_FOUND).body(entity)
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id:ID): ResponseEntity<Boolean> {
        val entity = basicCrud.deleteById(id)
        return ResponseEntity.status(if (entity) HttpStatus.OK else HttpStatus.NOT_FOUND).body(entity)
    }
}
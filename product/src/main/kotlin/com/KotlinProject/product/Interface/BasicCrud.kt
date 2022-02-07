package com.KotlinProject.product.Interface

interface BasicCrud<T, ID> {        // so it can be used by different objects
    fun findAll():List<T>           // returns a list of the input type
    fun findById(id:ID):T?          // incoming data ID and returns T type (null enabled)
    fun save(t:T):T                 // receives a parameter of type T and returns same type
    fun update(t:T):T               // receives a parameter of type T and returns same type
    fun deleteById(id:ID):T         // receives a parameter of type ID and returns same type
}
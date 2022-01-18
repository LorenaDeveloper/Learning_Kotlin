package com.KotlinProject.product.Model

import javax.validation.constraints.Min
import javax.validation.constraints.Size

data class Product(
    @get:Size(min = 3, max = 20)
    val name:String,
    @get:Min(0)
    var price:Double? = 1.5){
    override fun equals(other: Any?): Boolean {
        //first verify object not null
        other ?: return false //returns false if null
        if (other === this) return true
        if (this.javaClass != other.javaClass) return false

        // now we're sure we've got 2 objects of the same class
        // cast other to Product type
        other as Product

        // return true/false just by name comparison
        return this.name === other.name
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}
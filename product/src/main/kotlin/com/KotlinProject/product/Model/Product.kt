package com.KotlinProject.product.Model

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.validation.constraints.Min
import javax.validation.constraints.Size

@Entity
data class Product(
    @Id
    @get:Size(min = 3, max = 20)
    val name:String,
    @get:Min(0)
    var price:Double? = 1.5,
    @get:Min(0)
    var stock:Int = 0,
    @ManyToOne(cascade = [CascadeType.ALL]) //one product has one supplier, but one supplier has many products //imp cascade
    val provider:Provider){
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
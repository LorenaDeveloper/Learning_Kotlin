package com.KotlinProject.product.Model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.Size

@Entity
data class Provider (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //generates the autoincremental primaryKey automatically
    var id:Int = 0,
    @get:Size(min = 3, max=20)
    val name:String,
    val email:String){

        override fun equals(other: Any?): Boolean {
            //first verify object not null
            other ?: return false //returns false if null
            if (other === this) return true
            if (this.javaClass != other.javaClass) return false

            // now we're sure we've got 2 objects of the same class
            // cast other to Provider type
            other as Provider

            // return true/false just by name comparison
            return this.id === other.id
        }

        override fun hashCode(): Int {
            return id.hashCode()
        }
    }
package com.KotlinProject.product.Dto

import com.KotlinProject.product.Model.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductDTO:JpaRepository<Product, String>
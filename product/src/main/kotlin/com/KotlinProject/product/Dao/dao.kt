package com.KotlinProject.product.Dao

import com.KotlinProject.product.Model.Product
import com.KotlinProject.product.Model.Provider
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductDAO:JpaRepository<Product, String>

@Repository
interface ProviderDAO:JpaRepository<Provider, Int>
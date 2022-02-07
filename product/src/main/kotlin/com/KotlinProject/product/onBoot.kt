package com.KotlinProject.product

import com.KotlinProject.product.Model.Product
import com.KotlinProject.product.Model.Provider
import com.KotlinProject.product.Service.ProductService
import com.KotlinProject.product.Service.ProviderService
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class onBoot(private val productService: ProductService, private val providerService: ProviderService):ApplicationRunner {
    override fun run(args: ApplicationArguments?) {

        val defaultProvider = providerService.save(Provider(name="Company name", email = "company_email@gmail.com"))

        listOf(Product("apple", 5.0, 10, defaultProvider), Product("orange", 3.0, 15, defaultProvider)).forEach {
            println("Saving -> ${it.name}")
            productService.save(it)
        }
    }
}
package com.KotlinProject.product.Controller

import com.KotlinProject.product.Model.Product
import com.KotlinProject.product.Model.Provider
import com.KotlinProject.product.Service.ProductService
import com.KotlinProject.product.Service.ProviderService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.FileNotFoundException

//using abstract class
@RestController
@RequestMapping("/api/v1/product")
class ProductController(productService: ProductService):BasicController<Product, String>(productService){
    @GetMapping("/test")
    fun fileNotFoundTest(){
        throw FileNotFoundException("just test")
    }
}

@RestController
@RequestMapping("/api/v1/provider")
class ProviderController(providerService: ProviderService):BasicController<Provider, Int>(providerService)


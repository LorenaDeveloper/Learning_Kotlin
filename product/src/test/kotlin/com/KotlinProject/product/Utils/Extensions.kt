package com.KotlinProject.product.Utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.test.web.servlet.ResultActions

//transforms json to object
inline fun <reified T> ResultActions.bodyTo(mapper:ObjectMapper = jacksonObjectMapper()):T{
    return mapper.readValue(this.andReturn().response.contentAsString)
}
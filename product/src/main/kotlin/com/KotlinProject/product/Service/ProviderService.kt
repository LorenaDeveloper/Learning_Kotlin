package com.KotlinProject.product.Service

import com.KotlinProject.product.Dao.ProviderDAO
import com.KotlinProject.product.Interface.BasicCrud
import com.KotlinProject.product.Model.Provider
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class ProviderService(private  val providerDAO: ProviderDAO):BasicCrud<Provider, Int> {
    override fun findAll(): List<Provider> {
        return this.providerDAO.findAll()
    }

    override fun findById(id: Int): Provider? {
        return this.providerDAO.findByIdOrNull(id)
    }

    override fun save(t: Provider): Provider {
        return if(!this.providerDAO.existsById(t.id)) this.providerDAO.save(t) else throw org.springframework.dao.DuplicateKeyException("${t.id} already exists")
    }

    override fun update(t: Provider): Provider {
        return if(this.providerDAO.existsById(t.id)) this.providerDAO.save(t) else throw EntityNotFoundException("${t.id} doesn't exists")
    }

    override fun deleteById(id: Int): Provider {
        return this.findById(id)?.apply {
            this@ProviderService.providerDAO.deleteById(id)
        } ?: throw EntityNotFoundException("$id doesn't exists")
    }
}
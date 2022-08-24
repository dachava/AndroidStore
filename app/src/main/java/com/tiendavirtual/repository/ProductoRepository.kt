package com.tiendavirtual.repository

import androidx.lifecycle.MutableLiveData
import com.tiendavirtual.model.Producto
import com.tiendavirtual.data.ProductoDao

class ProductoRepository (private val productoDao: ProductoDao) {
    fun saveProducto(producto: Producto) {
        productoDao.saveProducto(producto)
    }

    fun deleteProducto(producto: Producto) {
        productoDao.deleteProducto(producto)
    }

    val getAllData : MutableLiveData<List<Producto>> = productoDao.getAllData()
}
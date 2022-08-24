package com.tiendavirtual.viewmodel
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tiendavirtual.data.ProductoDao
import com.tiendavirtual.model.Producto
import com.tiendavirtual.repository.ProductoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductoViewModel(application: Application) : AndroidViewModel(application) {
    //Atributo para obtener la lista de lugares en un ArrayList especial
    val getAllData: MutableLiveData<List<Producto>>
    //Atributo para acceder al repositorio de Lugar
    private val repository: ProductoRepository = ProductoRepository(ProductoDao())

    //Bloque de inicialización de los atributos
    init {  getAllData = repository.getAllData  }

    fun saveProducto(producto: Producto) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveProducto(producto)
        }
    }

    fun deleteProducto(producto: Producto) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteProducto(producto)
        }
    }

}
package com.tiendavirtual.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.tiendavirtual.model.Producto
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

class ProductoDao {
    private val coleccion1 = "tiendaVirtual"
    private val usuario= Firebase.auth.currentUser?.email.toString()
    private val coleccion2 = "prodsTienda"
    private var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    init {
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    fun getAllData() : MutableLiveData<List<Producto>> {
        val listaProductos = MutableLiveData<List<Producto>>()
        firestore.collection(coleccion1).document(usuario).collection(coleccion2)
            .addSnapshotListener{ instantanea, e ->
                if (e != null) {  //Se valida si se generó algún error en la captura de los documentos
                    return@addSnapshotListener
                }
                if (instantanea != null) {

                    val lista = ArrayList<Producto>()
                    val productos = instantanea.documents
                    productos.forEach {
                        val producto = it.toObject(Producto::class.java)
                        if (producto!=null) { lista.add(producto) }
                    }
                    listaProductos.value=lista
                }
            }
        return listaProductos
    }
    fun saveProducto(producto: Producto) {
        val documento: DocumentReference
        if (producto.id.isEmpty()) {  //Si id no tiene valor entonces es un documento nuevo
            documento = firestore.collection(coleccion1).document(usuario).collection(coleccion2).document()
            producto.id = documento.id
        } else {  //si el id tiene valor... entonces el documento existe... y recupero la info de él
            documento = firestore.collection(coleccion1).document(usuario)
                .collection(coleccion2).document(producto.id)
        }
        documento.set(producto)
            .addOnSuccessListener { Log.d("saveProducto","Se creó o modificó un producto") }
            .addOnCanceledListener { Log.e("saveProducto","NO se creó o modificó un producto") }
    }

    fun deleteProducto(producto: Producto) {
        if (producto.id.isNotEmpty()) {  //Si el id tiene valor... entonces podemos eliminar el lugar... porque existe...
            firestore.collection(coleccion1).document(usuario)
                .collection(coleccion2).document(producto.id).delete()
                .addOnSuccessListener { Log.d("deleteProducto","Se elimintó un producto") }
                .addOnCanceledListener { Log.e("deleteProducto","NO se eliminó un producto") }
        }
    }

}
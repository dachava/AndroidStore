package com.tiendavirtual.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.tiendavirtual.databinding.ProductoFilaBinding
import com.tiendavirtual.model.Producto
import com.tiendavirtual.ui.producto.ProductoFragmentDirections

class ProductoAdapter : RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>(){
    //una lista para gestionar la información de los lugares
    private var lista = emptyList<Producto>()

    inner class ProductoViewHolder(private val itemBinding: ProductoFilaBinding)
        : RecyclerView.ViewHolder (itemBinding.root){
        fun dibuja(producto: Producto) {
            itemBinding.tvNombre.text = producto.nombre
            itemBinding.tvDescripcion.text = producto.descripcion
            itemBinding.tvPrecio.text = producto.precio
            itemBinding.tvCantidad.text = producto.cantidad
            itemBinding.vistaFila.setOnClickListener {
                val accion = ProductoFragmentDirections
                    .actionNavProductoToUpdateProductoFragment(producto)
                itemView.findNavController().navigate(accion)
            }
        }
    }

    //Acá se va a crear una "cajita" del reciclador...  una fila... sólo la estructura
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val itemBinding =
            ProductoFilaBinding.inflate(LayoutInflater.from(parent.context),
                parent,false)
        return ProductoViewHolder(itemBinding)
    }

    //Acá se va a solicitar "dibujar" una cajita, según el elemento de la lista...
    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = lista[position]
        holder.dibuja(producto)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    fun setData(productos: List<Producto>) {
        lista = productos
        notifyDataSetChanged()
    }

}
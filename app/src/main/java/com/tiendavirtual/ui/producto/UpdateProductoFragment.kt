package com.tiendavirtual.ui.producto

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.*
import android.view.ViewGroup
import com.tiendavirtual.R
import com.tiendavirtual.databinding.FragmentUpdateProductoBinding
import com.tiendavirtual.model.Producto
import com.tiendavirtual.viewmodel.ProductoViewModel


class UpdateProductoFragment : Fragment() {


    private var _binding: FragmentUpdateProductoBinding? = null
    private val binding get() = _binding!!
    private lateinit var productoViewModel: ProductoViewModel
    //Se deciben los parametros pasados por argumento
    private val args by navArgs<UpdateProductoFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        productoViewModel =
            ViewModelProvider(this)[ProductoViewModel::class.java]
        _binding = FragmentUpdateProductoBinding.inflate(inflater, container, false)

        //Coloco la info del lugar en los campos del fragmento... para modificar...
        binding.etNombre.setText(args.producto.nombre)
        binding.etDescripcion.setText(args.producto.descripcion)
        binding.etPrecio.setText(args.producto.precio)
        binding.etCantidad.setText(args.producto.cantidad)
        binding.imagePreview.setImageURI(Uri.parse(args.producto.imagen))

        binding.btUpdateProducto.setOnClickListener { updateProducto() }


        //Se indica que esta pantalla tiene un menu personalizado...
        setHasOptionsMenu(true)

        return binding.root
    }

    private fun updateProducto() {
        val nombre=binding.etNombre.text.toString()
        val descripcion=binding.etDescripcion.text.toString()
        val precio=binding.etPrecio.text.toString()
        val cantidad=binding.etCantidad.text.toString()

        //val imageUri = binding.image_preview

        if (nombre.isNotEmpty()) { //Si puedo crear un lugar
            val producto= Producto(args.producto.id,nombre,descripcion,precio,cantidad,"")

            productoViewModel.saveProducto(producto)

            Toast.makeText(requireContext(),getString(R.string.msg_producto_update),Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateProductoFragment_to_nav_producto)
        } else {  //Mensaje de error...
            Toast.makeText(requireContext(),getString(R.string.msg_data),Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
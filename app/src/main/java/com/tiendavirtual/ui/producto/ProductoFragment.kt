package com.tiendavirtual.ui.producto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tiendavirtual.R
import com.tiendavirtual.adapter.ProductoAdapter
import com.tiendavirtual.databinding.FragmentProductoBinding
import com.tiendavirtual.viewmodel.ProductoViewModel


class ProductoFragment : Fragment() {

    private var _binding: FragmentProductoBinding? = null
    private val binding get() = _binding!!
    private lateinit var productoViewModel: ProductoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val productoViewModel =
            ViewModelProvider(this).get(ProductoViewModel::class.java)

        _binding = FragmentProductoBinding.inflate(inflater, container, false)
        binding.addProducto.setOnClickListener {
            findNavController().navigate(R.id.action_nav_producto_to_addProductoFragment)
        }

        //Activar el reciclador...
        val productoAdapter=ProductoAdapter()
        val reciclador = binding.reciclador
        reciclador.adapter = productoAdapter
        reciclador.layoutManager = LinearLayoutManager(requireContext())
        productoViewModel.getAllData.observe(viewLifecycleOwner) {
            productoAdapter.setData(it)
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
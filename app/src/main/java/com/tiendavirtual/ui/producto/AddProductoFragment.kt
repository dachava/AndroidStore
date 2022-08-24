package com.tiendavirtual.ui.producto

import android.Manifest
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tiendavirtual.R
import com.tiendavirtual.databinding.FragmentAddProductoBinding
import com.tiendavirtual.model.Producto
import com.tiendavirtual.viewmodel.ProductoViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
//import com.tiendavirtual.utiles.ImagenUtiles
import java.text.SimpleDateFormat
import java.util.*


class AddProductoFragment : Fragment() {
    private var progressDialog: ProgressDialog? = null
    private val PICK_IMAGE_REQUEST = 71
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null

    private var _binding: FragmentAddProductoBinding? = null
    private val binding get() = _binding!!
    private lateinit var productoViewModel: ProductoViewModel
    //private lateinit var imagenUtiles: ImagenUtiles
    private lateinit var tomarFotoActivity: ActivityResultLauncher<Intent>

    private var imageUri: Uri? = null
    private var imageString: String = ""



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        productoViewModel =
            ViewModelProvider(this)[ProductoViewModel::class.java]
        _binding = FragmentAddProductoBinding.inflate(inflater, container, false)
        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference

        binding.btAgregar.setOnClickListener {
            binding.progressBar.visibility = ProgressBar.VISIBLE
            binding.msgMensaje.visibility = TextView.VISIBLE
           addProducto()
        }

        binding.btnChooseImage.setOnClickListener {
            launchGallery()
        }

        binding.btnUploadImage.setOnClickListener {
            uploadImage()
        }


        return binding.root
    }

    private fun toggleDialogBar(show: Boolean) {
        if (this.progressDialog != null && this.progressDialog!!.isShowing) {
            this.progressDialog!!.dismiss()
        } else {
            this.progressDialog = ProgressDialog(this.context)
            this.progressDialog!!.setMessage("Uploading file ...")
            this.progressDialog!!.setCancelable(false)
            this.progressDialog!!.show()
        }
    }

    private fun uploadImage() {
        val name = binding.etNombre.toString()
        this.toggleDialogBar(true)

        val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
        val now = Date()
        val fileName = "_" + formatter.format(now)
        val storageReference = FirebaseStorage.getInstance().getReference("images/$name$fileName")

        this.toggleDialogBar(false)
        this.imageUri?.let {
            storageReference
                .putFile(it)
                .addOnFailureListener {
                    Toast.makeText(requireContext(),getString(R.string.fail_save),Toast.LENGTH_SHORT).show()
                }.addOnSuccessListener { taskSnapshot ->
                    binding.imagePreview.setImageURI(null)
                    Toast.makeText(requireContext(),getString(R.string.success_save),Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun launchGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        this.imageUri = data?.data
        binding.imagePreview.setImageURI(this.imageUri)
    }

    private fun addProducto() {
        val nombre=binding.etNombre.text.toString()
        val descripcion=binding.etDescripcion.text.toString()
        val precio=binding.etPrecio.text.toString()
        val cantidad=binding.etCantidad.text.toString()


        if (nombre.isNotEmpty()) {
            val producto= Producto("",nombre,descripcion,precio,cantidad,this.imageString)
            productoViewModel.saveProducto(producto)
            Toast.makeText(requireContext(),getString(R.string.msg_prod_added),Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addProductoFragment_to_nav_producto)
        } else {  //Mensaje de error...
            Toast.makeText(requireContext(),getString(R.string.msg_data),Toast.LENGTH_SHORT).show()
        }
    }


}
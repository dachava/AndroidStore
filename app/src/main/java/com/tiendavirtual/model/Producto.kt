package com.tiendavirtual.model
import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Producto(
    var id: String,
    val nombre: String,
    val descripcion: String?,
    val precio: String?,
    val cantidad: String?,
    val imagen: String?

) : Parcelable {
    constructor () :
            this("", "", "", "", "", "")
}

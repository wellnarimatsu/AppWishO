package br.com.wisho.model

import android.graphics.Bitmap
import android.net.Uri
import android.widget.ImageView
import java.math.BigDecimal

class Desejo(
    val nome : String,
    val descricao: String,
    val valor : BigDecimal,
    val link : String,
    val imagem : String? = null


) {
}
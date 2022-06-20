package br.com.wisho.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Parcelize
data class Desejo(
    val nome : String,
    val descricao: String,
    val valor : BigDecimal,
    val link : String,
    val imagem : String? = null


):Parcelable
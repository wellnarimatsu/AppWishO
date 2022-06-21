package br.com.wisho.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal
@Entity
@Parcelize
data class Desejo(
    @PrimaryKey(autoGenerate = true) val id : Long = 0L,
    val nome : String,
    val descricao: String,
    val valor : BigDecimal,
    val link : String,
    val imagem : String? = null


):Parcelable
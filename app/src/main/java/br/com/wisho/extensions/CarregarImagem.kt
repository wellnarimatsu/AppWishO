package br.com.wisho.extensions

import android.widget.ImageView
import br.com.wisho.R
import coil.load

fun ImageView.tentaCarregarImagem(url: String? = null){
    load(url){
        fallback(R.drawable.erro)
        error(R.drawable.erro)

    }
}
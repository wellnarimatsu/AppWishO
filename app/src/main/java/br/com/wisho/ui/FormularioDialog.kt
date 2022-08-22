package br.com.wisho.ui

import android.content.Context
import android.view.LayoutInflater.from
import androidx.appcompat.app.AlertDialog
import br.com.wisho.databinding.FormularioDialogBinding
import br.com.wisho.extensions.tentaCarregarImagem

class FormularioDialog(private val context: Context){

    fun showDialog(urlPadrao: String? = null,
                   quandoImagemCarregada: (imagem: String)-> Unit) {

        FormularioDialogBinding.inflate(from(context)).apply {

            urlPadrao?.let{
                imagemDialog.tentaCarregarImagem(it)
                urlDialog.setText(it)
            }

            //lógica para carregar img
            btnCarregarDialog.setOnClickListener{
                //acessando url
                val url = urlDialog.text.toString()
                imagemDialog.tentaCarregarImagem(url)


            }
            AlertDialog.Builder(context)

                .setView(root)
                .setPositiveButton("Confirmar"){_,_->
                    val url = urlDialog.text.toString()
                    quandoImagemCarregada(url)
                }

                .setNegativeButton("Cancelar"){_,_->}
                .show()
        }



    }
}
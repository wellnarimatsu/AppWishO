package br.com.wisho.ui

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult
import br.com.wisho.R
import br.com.wisho.dao.DesejosDao
import br.com.wisho.databinding.ActivityFormularioDesejosBinding
import br.com.wisho.model.Desejo
import br.com.wisho.ui.Formulario.Companion.IMAGE_REQUEST_CODE
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

class Formulario : AppCompatActivity() {

    private val uri : Uri? = null
    private val binding by lazy {
        ActivityFormularioDesejosBinding.inflate(layoutInflater)
    }

    companion object{
        val IMAGE_REQUEST_CODE = 1_000;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoSalvar()

        binding.imagemForm.setOnClickListener{
            imgGaleria()


        }
    }


    private fun configuraBotaoSalvar() {
        val dao = DesejosDao()
        binding.buttonAddDesejoForm.setOnClickListener {
            val novoDesejo = criaProduto()
            dao.adiciona(novoDesejo)
            finish()

        }
    }

    private fun criaProduto(): Desejo {
        val campoNome = binding.nomeForm
        val nome = campoNome.text.toString()
        val campoDesc = binding.descricaoForm
        val desc = campoDesc.text.toString()
        val campoValor = binding.valorForm
        val valorEmTexto = campoValor.text.toString()
        val valor = if (valorEmTexto.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valorEmTexto)
        }
        val campoLink = binding.linkForm
        val link = campoLink.text.toString()

        return Desejo(
            nome = nome,
            descricao = desc,
            valor = valor,
            link = link,
            imagem = uri
        )

    }
    private fun imgGaleria(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK){
            binding.imagemForm.setImageURI(data?.data)
        }
    }


    }



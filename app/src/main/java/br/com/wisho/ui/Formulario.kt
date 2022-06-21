package br.com.wisho.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.wisho.app.data.base.AppDataBase
import br.com.wisho.databinding.ActivityFormularioDesejosBinding
import br.com.wisho.extensions.tentaCarregarImagem
import br.com.wisho.model.Desejo
import java.math.BigDecimal

class Formulario : AppCompatActivity() {

    private var url: String? = null
    private val binding by lazy {
        ActivityFormularioDesejosBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoSalvar()
        title="Adicionando Desejo"

        binding.imagemFormulario.setOnClickListener{

            FormularioDialog(this).showDialog (url){ imagem->
                url = imagem
                binding.imagemFormulario.tentaCarregarImagem(url)
            }

            }

        }



    private fun configuraBotaoSalvar() {


        val db = AppDataBase.instancia(this)
        val desejoDao = db.desejoDao()

        binding.buttonAddDesejoForm.setOnClickListener {
            val novoDesejo = criaProduto()
            desejoDao.salva(novoDesejo)
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
            imagem = url
        )

    }




}

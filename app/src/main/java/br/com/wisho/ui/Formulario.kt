package br.com.wisho.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import br.com.wisho.R
import br.com.wisho.dao.DesejosDao
import br.com.wisho.databinding.ActivityFormularioDesejosBinding
import br.com.wisho.model.Desejo
import java.math.BigDecimal

class Formulario : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioDesejosBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoSalvar()
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
            link = link
        )

    }
}
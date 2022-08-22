package br.com.wisho.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.com.wisho.app.data.base.AppDataBase
import br.com.wisho.app.data.base.DesejosDao
import br.com.wisho.constantes.CHAVE_DESEJO_ID
import br.com.wisho.databinding.ActivityFormularioDesejosBinding
import br.com.wisho.extensions.tentaCarregarImagem
import br.com.wisho.model.Desejo
import kotlinx.coroutines.launch
import java.math.BigDecimal

class Formulario : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioDesejosBinding.inflate(layoutInflater)
    }
    private var url: String? = null
    private var idDesejo = 0L
    private val desejoDao: DesejosDao by lazy {

        val db = AppDataBase.instancia(this)
        db.desejoDao()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoSalvar()
        title = "Adicionando Desejo"

        binding.imagemFormulario.setOnClickListener {

            FormularioDialog(this).showDialog(url) { imagem ->
                url = imagem
                binding.imagemFormulario.tentaCarregarImagem(url)
            }
        }

        tentaCarregarDesejo()

    }

    private fun tentaCarregarDesejo() {
        idDesejo = intent.getLongExtra(CHAVE_DESEJO_ID, 0L)
    }


    override fun onResume() {
        super.onResume()
        tentaBuscarDesejo()
    }

    private fun tentaBuscarDesejo() {


        lifecycleScope.launch {
            desejoDao.buscaPorId(idDesejo).collect {
                it?.let { desejoEncontrado ->
                    title = "Editando Desejo"
                    preencheCampos(desejoEncontrado)
                }
            }
        }
    }


    private fun preencheCampos(desejoCarregado: Desejo) {
//        idDesejo = desejoCarregado.id
        url = desejoCarregado.imagem
        binding.imagemFormulario.tentaCarregarImagem(desejoCarregado.imagem)
        binding.nomeForm.setText(desejoCarregado.nome)
        binding.descricaoForm.setText(desejoCarregado.descricao)
        binding.valorForm.setText(desejoCarregado.valor.toPlainString())
//        binding.linkForm.setText(desejoCarregado.link)
    }


    private fun configuraBotaoSalvar() {
        binding.buttonAddDesejoForm.setOnClickListener {
            val novoDesejo = criaDesejo()
//            if(idDesejo > 0){
//                desejoDao.editar(novoDesejo)
//                finish()
//            }else{
//                desejoDao.salva(novoDesejo)
//
//
//            }

            lifecycleScope.launch {
                desejoDao.salva(novoDesejo)
                finish()
            }
        }
    }

    private fun criaDesejo(): Desejo {
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
//        val campoLink = binding.linkForm
//        val link = campoLink.text.toString()

        return Desejo(
            id = idDesejo,
            nome = nome,
            descricao = desc,
            valor = valor,
//            link = link,
            imagem = url
        )
    }
}

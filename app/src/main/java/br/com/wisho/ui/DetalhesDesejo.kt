package br.com.wisho.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.wisho.R
import br.com.wisho.constantes.CHAVE_DESEJO
import br.com.wisho.databinding.ActivityDetalhesDesejoBinding
import br.com.wisho.extensions.formatarParaReal
import br.com.wisho.extensions.tentaCarregarImagem
import br.com.wisho.model.Desejo

class DetalhesDesejo : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetalhesDesejoBinding.inflate(layoutInflater)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tentaCarregarProduto()

    }

    private fun tentaCarregarProduto(){
        intent.getParcelableExtra<Desejo>(CHAVE_DESEJO)?.let { desejoCarregado ->
            preencheCampos(desejoCarregado)
        }?: finish()
    }
    private fun preencheCampos(desejoCarregado:Desejo){
        with(binding){
            imagemDetalhes.tentaCarregarImagem(desejoCarregado.imagem)
            nomeDetalhes.text = desejoCarregado.nome
            descDetalhes.text = desejoCarregado.descricao
            valorDetalhes.text= desejoCarregado.valor.formatarParaReal()
            linkDetalhes.text= desejoCarregado.link

        }
    }
}
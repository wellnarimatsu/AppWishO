package br.com.wisho.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import br.com.wisho.R
import br.com.wisho.app.data.base.AppDataBase
import br.com.wisho.constantes.CHAVE_DESEJO
import br.com.wisho.databinding.ActivityDetalhesDesejoBinding
import br.com.wisho.extensions.formatarParaReal
import br.com.wisho.extensions.tentaCarregarImagem
import br.com.wisho.model.Desejo

class DetalhesDesejo : AppCompatActivity() {

    private lateinit var desejo: Desejo
    private val binding by lazy {
        ActivityDetalhesDesejoBinding.inflate(layoutInflater)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tentaCarregarProduto()

    }

    private fun tentaCarregarProduto() {
        intent.getParcelableExtra<Desejo>(CHAVE_DESEJO)?.let { desejoCarregado ->
            preencheCampos(desejoCarregado)
            desejo = desejoCarregado


        } ?: finish()
    }

    private fun preencheCampos(desejoCarregado: Desejo) {
        with(binding) {
            imagemDetalhes.tentaCarregarImagem(desejoCarregado.imagem)
            nomeDetalhes.text = desejoCarregado.nome
            descDetalhes.text = desejoCarregado.descricao
            valorDetalhes.text = desejoCarregado.valor.formatarParaReal()
            linkDetalhes.text = desejoCarregado.link

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalhes, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (::desejo.isInitialized) {
            val db = AppDataBase.instancia(this)
            val desejoDao = db.desejoDao()

            when (item.itemId) {
                R.id.menu_delete -> {
                    desejoDao.deletar(desejo)
                }
                R.id.menu_editar -> {
                    Intent(this, Formulario::class.java).apply {
                        putExtra(CHAVE_DESEJO, desejo)
                        startActivity(this)
                    }

                }

            }
        }

        return super.onOptionsItemSelected(item)
    }
}
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
    private var desejoId: Long? = null
    private var desejo : Desejo? = null
    private val binding by lazy {
        ActivityDetalhesDesejoBinding.inflate(layoutInflater)

    }
    val desejoDao by lazy {
        AppDataBase.instancia(this).desejoDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tentaCarregarProduto()

    }

    override fun onResume() {
        super.onResume()
        desejoId?.let{id ->
            desejo = desejoDao.buscaPorId(id)
        }
        desejo?.let {
            preencheCampos(it)
        }?: finish()
    }

    private fun tentaCarregarProduto() {
        intent.getParcelableExtra<Desejo>(CHAVE_DESEJO)?.let { desejoCarregado ->
            desejoId = desejoCarregado.id


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


            when (item.itemId) {
                R.id.menu_delete -> {
                    desejo?.let { desejoDao.deletar(it) }
                }
                R.id.menu_editar -> {
                    Intent(this, Formulario::class.java).apply {
                        putExtra(CHAVE_DESEJO, desejo)
                        startActivity(this)
                    }

                }

            }


        return super.onOptionsItemSelected(item)
    }
}
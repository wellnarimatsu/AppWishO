package br.com.wisho.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.com.wisho.R
import br.com.wisho.app.data.base.AppDataBase
import br.com.wisho.constantes.CHAVE_DESEJO_ID
import br.com.wisho.databinding.ActivityDetalhesDesejoBinding
import br.com.wisho.extensions.formatarParaReal
import br.com.wisho.extensions.tentaCarregarImagem
import br.com.wisho.model.Desejo
import kotlinx.coroutines.launch

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


        binding.linkDetalhes.setOnClickListener {

            val queryUri = Uri.parse("")
            val intentGoogle = Intent(Intent.ACTION_VIEW,queryUri)
            val intent = Intent.createChooser(intentGoogle, "Abrir site")

            startActivity(intent)
        }
    }




    override fun onResume() {
        super.onResume()
        buscaDesejoNoBanco()
    }

    private fun buscaDesejoNoBanco() {

    lifecycleScope.launch{
        desejoId?.let { id ->
            desejo = desejoDao.buscaPorId(id)
        }
        desejo?.let {
            preencheCampos(it)
        } ?: finish()

    }

    }

    private fun tentaCarregarProduto() {
        desejoId = intent.getLongExtra(CHAVE_DESEJO_ID,0L)

        finish()
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

                    lifecycleScope.launch{
                        desejo?.let { desejoDao.deletar(it)
                            finish()}
                    }


                }
                R.id.menu_editar -> {
                    Intent(this, Formulario::class.java).apply {
                        putExtra(CHAVE_DESEJO_ID, desejoId)
                        startActivity(this)
                    }

                }

            }


        return super.onOptionsItemSelected(item)
    }
}
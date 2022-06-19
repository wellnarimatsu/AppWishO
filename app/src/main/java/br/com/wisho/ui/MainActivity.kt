package br.com.wisho.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.wisho.dao.DesejosDao
import br.com.wisho.databinding.ActivityMainBinding
import br.com.wisho.recyclerview.adapter.ListaAdapter

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val dao = DesejosDao()
    private val adapter =  ListaAdapter(context = this, desejos = dao.buscaTodos())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraFab()
        configuraRecyclerView()

    }

    override fun onResume() {
        super.onResume()
        adapter.atualiza(dao.buscaTodos())


    }
    private fun configuraFab(){
        val fab = binding.fabButton

        fab.setOnClickListener {
            vaiParaFormualrio()
        }
    }

    private fun vaiParaFormualrio(){
        val intent = Intent(this,Formulario::class.java)
        startActivity(intent)

    }
    private fun configuraRecyclerView(){
        binding.recyclerView.adapter = adapter

    }
}
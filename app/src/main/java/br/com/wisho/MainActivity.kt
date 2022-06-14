package br.com.wisho

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import br.com.wisho.dao.DesejosDao
import br.com.wisho.model.Desejo
import br.com.wisho.recyclerview.adapter.ListaAdapter
import br.com.wisho.ui.Formulario
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val dao = DesejosDao()
    private val adapter =  ListaAdapter(context = this, desejos = dao.buscaTodos())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configuraFab()
        configuraRecyclerView()

    }

    override fun onResume() {
        super.onResume()
        adapter.atualiza(dao.buscaTodos())


    }
    private fun configuraFab(){
        val fab = findViewById<FloatingActionButton>(R.id.fab_button)
        fab.setOnClickListener {
            vaiParaFormualrio()
        }
    }

    private fun vaiParaFormualrio(){
        Intent(this,Formulario::class.java)
        startActivity(intent)

    }
    private fun configuraRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = adapter

    }
}
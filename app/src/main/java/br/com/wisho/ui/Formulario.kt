package br.com.wisho.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import br.com.wisho.R
import br.com.wisho.dao.DesejosDao
import br.com.wisho.model.Desejo
import java.math.BigDecimal

class Formulario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_desejos)



        val buttonAdd = findViewById<Button>(R.id.button_add_desejo_form)

        buttonAdd.setOnClickListener{

            val campoNome = findViewById<EditText>(R.id.nome_form)
            val nome = campoNome.text.toString()
            val campoDesc = findViewById<EditText>(R.id.descricao_form)
            val desc = campoDesc.text.toString()
            val campoValor = findViewById<EditText>(R.id.valor_form)
            val valorEmTexto = campoValor.text.toString()
            val valor = if(valorEmTexto.isBlank()){
                BigDecimal.ZERO
            }else {
                BigDecimal(valorEmTexto)
            }
            val campoLink = findViewById<EditText>(R.id.link_form)
            val link = campoLink.text.toString()

            val novoDesejo = Desejo(
                nome = nome,
                descricao = desc,
                valor = valor,
                link = link
            )

            val dao = DesejosDao()
            dao.adiciona(novoDesejo)


            finish()

        }
    }
}
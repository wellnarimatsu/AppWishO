package br.com.wisho.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.wisho.R
import br.com.wisho.model.Desejo

class ListaAdapter(
    private val context: Context,
    desejos: List<Desejo>

) : RecyclerView.Adapter<ListaAdapter.ViewHolder>(){

    private val desejos = desejos.toMutableList()

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){

        fun vincular(desejos: Desejo){
            val nome = itemView.findViewById<TextView>(R.id.nome_card)
            nome.text = desejos.nome
            val descricao = itemView.findViewById<TextView>(R.id.descricao_card)
            descricao.text = desejos.descricao
            val valor = itemView.findViewById<TextView>(R.id.valor_card)
            valor.text = desejos.valor.toPlainString()
            val link = itemView.findViewById<TextView>(R.id.link_card)
            link.text = desejos.link

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.card_view, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val desejo = desejos[position]
        holder.vincular(desejo)
    }

    override fun getItemCount(): Int {
        return desejos.size
    }

    fun atualiza(desejos: List<Desejo>){
        this.desejos.clear()
        this.desejos.addAll(desejos)
        notifyDataSetChanged()
    }


}

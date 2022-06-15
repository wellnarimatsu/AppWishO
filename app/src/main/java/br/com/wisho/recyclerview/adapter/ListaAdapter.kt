package br.com.wisho.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.wisho.R
import br.com.wisho.databinding.CardViewBinding
import br.com.wisho.model.Desejo

class ListaAdapter(
    private val context: Context,
    desejos: List<Desejo>

) : RecyclerView.Adapter<ListaAdapter.ViewHolder>(){

    private val desejos = desejos.toMutableList()

    class ViewHolder(private val binding: CardViewBinding) : RecyclerView.ViewHolder(binding.root){

        fun vincular(desejos: Desejo){
            val nome = binding.nomeCard
            nome.text = desejos.nome
            val descricao = binding.descricaoCard
            descricao.text = desejos.descricao
            val valor = binding.valorCard
            valor.text = desejos.valor.toPlainString()
            val link = binding.linkCard
            link.text = desejos.link

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = CardViewBinding.inflate(inflater,parent,false)

        return ViewHolder(binding)
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

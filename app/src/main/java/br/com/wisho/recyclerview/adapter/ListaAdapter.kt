package br.com.wisho.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.wisho.databinding.CardViewBinding
import br.com.wisho.extensions.formatarParaReal
import br.com.wisho.extensions.tentaCarregarImagem
import br.com.wisho.model.Desejo
import coil.load
import java.util.*

class ListaAdapter(

    private val context: Context,
    desejos: List<Desejo> = emptyList(),
    var quandoClicaNoItem: (desejo:Desejo) -> Unit = {}

) : RecyclerView.Adapter<ListaAdapter.ViewHolder>(){

    private val desejos = desejos.toMutableList()




    inner class ViewHolder(private val binding: CardViewBinding) : RecyclerView.ViewHolder(binding.root){

        private lateinit var desejo : Desejo

        init {
            itemView.setOnClickListener{
                if (::desejo.isInitialized){
                    quandoClicaNoItem(desejo)
                }

            }
        }

        fun vincular(desejo: Desejo){
            this.desejo = desejo
            val nome = binding.nomeCard
            nome.text = desejo.nome
            val descricao = binding.descricaoCard
            descricao.text = desejo.descricao
            val valor = binding.valorCard
            val valorEmMoeda: String = desejo.valor.formatarParaReal()
            valor.text = valorEmMoeda
//            val link = binding.linkCard
//            link.text = desejo.link

            val visibilidade = if (desejo.imagem != null){
                View.VISIBLE
            }else{
                View.GONE
            }
            binding.imagemCard.visibility = visibilidade

            binding.imagemCard.tentaCarregarImagem(desejo.imagem)
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

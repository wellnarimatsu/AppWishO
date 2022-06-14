package br.com.wisho.dao

import br.com.wisho.model.Desejo

class DesejosDao {
    fun adiciona (desejo: Desejo){
        Companion.desejo.add(desejo)
    }

    fun buscaTodos(): List<Desejo>{
        return desejo.toList()
    }

    companion object{
        private val desejo = mutableListOf<Desejo>()
    }
}
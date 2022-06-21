package br.com.wisho.app.data.base

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.wisho.model.Desejo

@Dao
interface DesejosDao {
    @Query("SELECT * FROM Desejo")
    fun buscaDesejos(): List<Desejo>


    @Insert
    fun salva(vararg desejo: Desejo)
}
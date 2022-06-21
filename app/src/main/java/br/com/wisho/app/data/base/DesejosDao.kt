package br.com.wisho.app.data.base

import androidx.room.*
import br.com.wisho.model.Desejo

@Dao
interface DesejosDao {
    @Query("SELECT * FROM Desejo")
    fun buscaDesejos(): List<Desejo>


    @Insert
    fun salva(vararg desejo: Desejo)

    @Delete
    fun deletar(desejo: Desejo)

    @Update
    fun editar(desejo: Desejo)
}

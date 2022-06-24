package br.com.wisho.app.data.base

import androidx.room.*
import br.com.wisho.model.Desejo

@Dao
interface DesejosDao {
    @Query("SELECT * FROM Desejo")
    fun buscaDesejos(): List<Desejo>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun salva(vararg desejo: Desejo)

    @Delete
    fun deletar(desejo: Desejo)

//    @Update
//    fun editar(desejo: Desejo)

    @Query("SELECT * FROM Desejo WHERE id = :id")
    fun buscaPorId(id:Long) : Desejo?
}

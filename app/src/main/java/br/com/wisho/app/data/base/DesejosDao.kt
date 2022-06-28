package br.com.wisho.app.data.base

import androidx.room.*
import br.com.wisho.model.Desejo
import kotlinx.coroutines.flow.Flow

@Dao
interface DesejosDao {
    @Query("SELECT * FROM Desejo")
    fun buscaDesejos(): Flow<List<Desejo>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun salva(vararg desejo: Desejo)

    @Delete
    suspend fun deletar(desejo: Desejo)

//    @Update
//    fun editar(desejo: Desejo)

    @Query("SELECT * FROM Desejo WHERE id = :id")
    fun buscaPorId(id:Long) : Flow<Desejo?>
}

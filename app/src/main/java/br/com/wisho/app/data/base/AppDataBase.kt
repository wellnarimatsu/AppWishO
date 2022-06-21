package br.com.wisho.app.data.base

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.wisho.Converters.Converter
import br.com.wisho.model.Desejo



@TypeConverters(Converter::class)
abstract class AppDataBase : RoomDatabase() {

    abstract fun desejoDao(): DesejosDao


    companion object{
        fun instancia(context: Context): AppDataBase{
            return Room.databaseBuilder(context,AppDataBase::class.java,"wisho.db")
                .allowMainThreadQueries()
                .build()
        }
    }
}


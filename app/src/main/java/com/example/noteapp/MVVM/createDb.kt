package com.example.noteapp.MVVM

import android.content.Context
import androidx.room.*
@Database(entities = [entity::class], version = 1, exportSchema = false)
abstract class createDb:RoomDatabase(){
    abstract fun myDao():Daoe
    companion object{
        @Volatile
        var INSTANCE:createDb?=null
        fun getDatabaseInstance(context: Context):createDb{
            val tempInstance= INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val roomDatabaseInstance=Room.databaseBuilder(context,createDb::class.java,"MyNotes").allowMainThreadQueries().build()
                INSTANCE=roomDatabaseInstance
                return roomDatabaseInstance
            }
        }
    }
}
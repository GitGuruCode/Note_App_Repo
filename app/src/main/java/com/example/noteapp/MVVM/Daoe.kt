package com.example.noteapp.MVVM

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.*
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Daoe {
    @Query("SELECT * FROM MyNotes")
    fun getMyNotes():LiveData<List<entity>>

    @Query("SELECT * FROM MyNotes WHERE priority=1")
    fun getHighNotes():LiveData<List<entity>>

    @Query("SELECT * FROM MyNotes WHERE priority=2")
    fun getMediumNotes():LiveData<List<entity>>

    @Query("SELECT * FROM MyNotes WHERE priority=3")
    fun getLowNotes():LiveData<List<entity>>

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    fun insertMyNotes(notes: entity)

    @Query("DELETE FROM MyNotes WHERE id=:id")
    fun deleteMyNotes(id:Int)

    @Update
    fun updateMyNotes(notes: entity)
}
package com.example.noteapp.MVVM

import androidx.lifecycle.LiveData

class Repository(val DAO:Daoe) {
    fun getAllNotes(): LiveData<List<entity>> =DAO.getMyNotes()
     fun getHighNotes():LiveData<List<entity>> =DAO.getHighNotes()
    fun getMediumNotes():LiveData<List<entity>> =DAO.getMediumNotes()
    fun getLowNotes():LiveData<List<entity>> =DAO.getLowNotes()
    fun insertNotes(notes: entity){
        DAO.insertMyNotes(notes)
    }
    fun deleteNotes(id:Int){
        DAO.deleteMyNotes(id)
    }
    fun updateNotes(notes:entity){
        DAO.updateMyNotes(notes)
    }
}
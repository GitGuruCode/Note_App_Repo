package com.example.noteapp.MVVM

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class viewModel(application: Application):AndroidViewModel(application) {
     val RepoObj:Repository
    init {
     val DaoObj=createDb.getDatabaseInstance(application).myDao()
       RepoObj= Repository(DaoObj)
    }
    fun addNotes(notes: entity){
        RepoObj.insertNotes(notes)
    }
    fun getNotes():LiveData<List<entity>> =RepoObj.getAllNotes()
    fun getHighNotes():LiveData<List<entity>> =RepoObj.getHighNotes()
    fun getMediumNotes():LiveData<List<entity>> =RepoObj.getMediumNotes()
    fun getLowNotes():LiveData<List<entity>> =RepoObj.getLowNotes()
    fun updateNotes(notes:entity){
        RepoObj.updateNotes(notes)
    }
    fun deleteNotes(id:Int){
        RepoObj.deleteNotes(id)
    }
}
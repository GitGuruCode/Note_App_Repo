package com.example.noteapp.MVVM

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "MyNotes")
@Parcelize
class entity (
    @PrimaryKey(autoGenerate = true)
    var id:Int? =null,
    var title:String,
    var subtitle:String,
    var notes:String,
    var date:String,
    var priority:String
        ):Parcelable





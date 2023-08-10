package com.example.todoapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todoapp.data.entity.Note

@Database(entities = [Note::class], version = 1)
abstract class DataBaseNote : RoomDatabase() {

    abstract fun getNoteDao():NoteDao

    //normalde buraxa ersilirdi ama hilt kullandgım için appmodulden erişicem

}
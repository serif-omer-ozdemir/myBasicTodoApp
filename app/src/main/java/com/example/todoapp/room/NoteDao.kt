package com.example.todoapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todoapp.data.entity.Note

@Dao
interface NoteDao {

    //tum notları oku
    @Query("SELECT * FROM notlar")
    suspend fun allNotes(): List<Note>

    //arama
    @Query("SELECT * FROM notlar WHERE note_title LIKE '%'||:searchWord ||'%'")
    suspend fun notesFind(searchWord: String): List<Note>

    //sil
    @Delete
    suspend fun notesDelete(note:Note)

    //guncelle
    @Update
    suspend fun notesUpdate(searchNote: Note)


    //ekle
    @Insert
    suspend fun notesAdd(note:Note)

}
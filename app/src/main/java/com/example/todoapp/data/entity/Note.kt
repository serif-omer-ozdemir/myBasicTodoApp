package com.example.todoapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "notlar")
data class Note(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("note_id") @NotNull var note_id: Int,
    @ColumnInfo("note_title") @NotNull var note_title: String,
    @ColumnInfo("note_content") @NotNull var note_content: String,
    @ColumnInfo("note_date") @NotNull var note_date: String
) : Serializable //veri transferini saglar bol parcaka gonder

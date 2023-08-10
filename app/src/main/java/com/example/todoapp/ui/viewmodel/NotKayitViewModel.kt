package com.example.todoapp.ui.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.todoapp.data.repo.NoteDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotKayitViewModel @Inject constructor(val repo:NoteDaoRepository) : ViewModel() {


    fun fabBtnKayit(not_baslik: String, not_icerik: String,note_tarihh:String,view: View) {
        repo.fabNoteKayit(not_baslik,not_icerik,note_tarihh,view)
    }

}
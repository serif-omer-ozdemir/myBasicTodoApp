package com.example.todoapp.ui.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.todoapp.data.repo.NoteDaoRepository
import com.example.todoapp.databinding.FragmentNotGuncelleBinding
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class NotGuncelleViewModel @Inject constructor(val repo:NoteDaoRepository): ViewModel() {



    fun fabBtnGuncelle(not_baslik: String, not_icerik: String,view: View) {
        repo.fabNoteGuncelle(not_baslik,not_icerik, view )
    }


}
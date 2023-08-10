package com.example.todoapp.ui.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.todoapp.data.repo.NoteDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LogInLogOutViewModel @Inject constructor(val repo: NoteDaoRepository) : ViewModel() {


    fun girisButonuTikla(eMail: String, password: String,view: View) {
        repo.butonGirisYap(eMail,password,view)
    }

    fun kaydetButonuTikla(eMail: String, password: String,view: View) {
        repo.butonKayitEt(eMail,password,view)


    }

    fun surekliAcikKal(view:View) {
        repo.surekliGiris(view)
    }



}
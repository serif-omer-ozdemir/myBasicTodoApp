package com.example.todoapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todoapp.data.entity.Note
import com.example.todoapp.data.repo.NoteDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnasayfaViewModel @Inject constructor(val repo:NoteDaoRepository) : ViewModel() {

    //viewmodelde toast ,snacbaar ,sayfa gecisleri içi intent gorsel nesne ile ilgili işlemler olmaz!!

    fun noteKelimeAra(aramaKelimesi: String) {
        repo.NoteAra(aramaKelimesi)
    }

    fun NoteSil(note_id: Int) {
        //bu işlemi adapter sinifinda vapicagiz adapterin view mode sınıfı olmadıgı
        // için bende anasayfa viewmodeline ekledim adapter sayfasinda anasayfa viewmodelden bır mesne alicak al
        //tabiki anasayfa viewmodelı beslemek için anasayfa fragmnet ı kullanıcagız
        repo.secilenNoteSil(note_id)
    }


    var notListesi=MutableLiveData<List<Note>>()

    init {
        tumNotlariYukle()
        notListesi=repo.notlariGetir()
    }

    fun tumNotlariYukle(){
        repo.tumkisiler()
    }

}
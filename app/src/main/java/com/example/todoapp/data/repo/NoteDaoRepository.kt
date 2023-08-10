package com.example.todoapp.data.repo

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import com.example.todoapp.R
import com.example.todoapp.data.entity.Note
import com.example.todoapp.room.NoteDao
import com.example.todoapp.util.gecisYap
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NoteDaoRepository @Inject constructor(
    var noteDao: NoteDao,
    var contexIm: Context,
    var auth: FirebaseAuth
) {
    //noteDao dao işlemlerini yapabılmemı sagllıcak
    //normal bır sınıf olucak nunun uzerinden odiğer viewmodellerde ortak olan işlemleri yapıcam


    var note_listesi: MutableLiveData<List<Note>>  //bu live data secilen yeri dinleyecek değişiklık oldugunda tetıklenecek

    init {
        note_listesi = MutableLiveData()
    }

    fun notlariGetir(): MutableLiveData<List<Note>> {
        return note_listesi
    }

    fun fabNoteKayit(not_baslik: String, not_icerik: String, note_tarihh: String,view: View) {

        val job = CoroutineScope(Dispatchers.Main).launch {
            val addes_note = Note(
                0,
                not_baslik,
                not_icerik,
                note_tarihh
            ) //id rasgele ver arka planda otomatık degişicek
            noteDao.notesAdd(addes_note)


            Navigation.gecisYap(view, R.id.action_notKayitFragment_to_anasayfaFragment)


        }
    }

    fun fabNoteGuncelle(not_baslik: String, not_icerik: String,view: View) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            val updates_note = Note(0, not_baslik, not_icerik, " ")
            noteDao.notesUpdate(updates_note)

            Navigation.gecisYap(view, R.id.action_notGuncelleFragment_to_anasayfaFragment)
        }
    }

    fun NoteAra(searchWord: String) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            note_listesi.value = noteDao.notesFind(searchWord)
        }
    }

    fun secilenNoteSil(note_id: Int) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            val deletes_note = Note(note_id, "x", "y", "z") //id dişindakılerın bır onemı yok
            noteDao.notesDelete(deletes_note)
            tumkisiler()
        }
    }

    fun tumkisiler() { //sadece burada  liveData kullanılacak
        //coroutine
        val job = CoroutineScope(Dispatchers.Main).launch {
            note_listesi.value = noteDao.allNotes()
        }
    }


    fun butonKayitEt(eMail: String, password: String, view: View) {
        // auth.currentUser guncel kullanıcıyı al


        auth.createUserWithEmailAndPassword(eMail, password).addOnCompleteListener {
            //bu cod blogu asenkron calısırı işlem tamamladıgından bıze haber verır

            if (it.isSuccessful) { //eger işlem basarılı olursa diğer activity e git
                Navigation.gecisYap(view, R.id.action_logInLogOutFragment_to_anasayfaFragment)

                Toast.makeText(contexIm, "Hosgeldin ${auth.currentUser}", Toast.LENGTH_LONG).show()
            }

        }.addOnFailureListener {
            //hata olursa ne yapıcak
            Toast.makeText(contexIm, it.localizedMessage, Toast.LENGTH_LONG).show()
        }



    }

    fun butonGirisYap(eMail: String, password: String, view: View) {

        auth.signInWithEmailAndPassword(eMail, password).addOnCompleteListener {

            //asenkron calısır

            if (it.isSuccessful) { //eger işlem basarılı olursa diğer activity e git
                Navigation.gecisYap(view, R.id.action_logInLogOutFragment_to_anasayfaFragment)

                Toast.makeText(contexIm, "Hosgeldin ${auth.currentUser?.email}", Toast.LENGTH_LONG)
                    .show() //guncel kullanıcınınn email adresini yazdır
            }

        }.addOnFailureListener {
            //hata olursa ne yapıcak
            Toast.makeText(contexIm, it.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }

    fun surekliGiris(view: View) {

        //firebase otomatık gırıs işlemleri
        val guncelKullanici = auth.currentUser // nullable doner

        if (guncelKullanici != null) { // yani kullanıcı gırıs yapmıssa
            Navigation.gecisYap(view, R.id.action_logInLogOutFragment_to_anasayfaFragment)
        }

    }
































}
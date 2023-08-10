package com.example.todoapp.di

import android.content.Context
import androidx.room.Room
import com.example.todoapp.data.repo.NoteDaoRepository
import com.example.todoapp.room.DataBaseNote
import com.example.todoapp.room.NoteDao
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)    //yenı her yerı besle
class AppModule {

    //ihtiyac duydygymuz degerlerı saglıcak olan yer burası


   // NoteDaoRepositoryde bir bağımlılıgım oldugu için ekledım
    @Provides //saglayici
    @Singleton  //heryerden erısılsın
    fun provideNoteDaoRepository(noteDao: NoteDao,@ApplicationContext context: Context,auth: FirebaseAuth):NoteDaoRepository{
        //mesela bagımlılıgım kısıler daoya ise provide.... seklınde adlandırılır
        // baska bagımlılıgım olursa onlar içinde bu sekılde fun yazmam gerekır
        return NoteDaoRepository(noteDao,context,auth)

    }

    //usteki notedao için altakı metodu olusturduk ona veri  sağlayacak

    //veri tabanı ekleme işlemi
    @Provides //saglayici
    @Singleton  //heryerden erısılsın
    fun provideNoteDao(@ApplicationContext context:Context):NoteDao{  //kendısı otomatık saglıyacak contexi
      //veritabanına erişme ve kopyalama işlemı
        val vt = Room.databaseBuilder(context, DataBaseNote::class.java, "notes.sqlite")
            .createFromAsset("notes.sqlite").build()
        return vt.getNoteDao()
    }


    //provideNoteDaoRepository deki firebase değiskenıni beslicek
    @Provides
    @Singleton
    fun provideFirebase():FirebaseAuth{
        return FirebaseAuth.getInstance()
    }


}
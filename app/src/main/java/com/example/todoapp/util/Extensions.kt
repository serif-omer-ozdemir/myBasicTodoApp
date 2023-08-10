package com.example.todoapp.util

import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.example.todoapp.R

//bu bir dosya ve amacı extesıons metodlar uretmek

fun Navigation.gecisYap(it:View ,id:Int){
    findNavController(it).navigate(id)
}

fun Navigation.gecisYap(it:View ,id:NavDirections){ //aynı isimde parametre turleri farkli ıkı fon olusturuyorum
    findNavController(it).navigate(id)
}
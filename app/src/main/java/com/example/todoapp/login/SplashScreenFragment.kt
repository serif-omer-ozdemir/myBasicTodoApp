package com.example.todoapp.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.todoapp.R
import com.example.todoapp.ui.fragment.AnasayfaFragment
import com.example.todoapp.util.gecisYap
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreenFragment : Fragment() {
    private val spaleshTime = 1000


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_splash_screen, container, false)


        Handler(Looper.myLooper()!!).postDelayed({

            findNavController().navigate(R.id.action_splashScreenFragment_to_logInLogOutFragment)


        }, spaleshTime.toLong())



        return view
    }


}
package com.example.todoapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentYeniNotEkleBinding
import com.example.todoapp.ui.viewmodel.NotKayitViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date

@AndroidEntryPoint
class NotKayitFragment : Fragment() {

    private lateinit var binding: FragmentYeniNotEkleBinding
    private lateinit var vm: NotKayitViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_yeni_not_ekle, container, false)

        //tarıh işlemleri


        binding.apply {
            noteKayitFragment = this@NotKayitFragment// data bındıng basglam aişlemı gerceklesti
            noteKayitToolbarBaslik = "Not Yeni Kayıt"
            val time = Date()
            val df = SimpleDateFormat("yyyy/MM/dd")
            tarih = (df.format(time)).toString()
        }

        return binding.root
    }

    fun fabKayit(not_baslik: String, not_icerik: String, note_tarihh: String,view: View) {

        if (not_baslik.isNotEmpty() || not_icerik.isNotEmpty()) {
            vm.fabBtnKayit(not_baslik, not_icerik, note_tarihh,view)
        } else {
            Toast.makeText(requireContext(), "Bos alanları doldur kenan", Toast.LENGTH_LONG).show()

        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp: NotKayitViewModel by viewModels()  //bu bir delegate yapısı oldugu için direkt değer atayamıyorum
        this.vm = temp
    }


}
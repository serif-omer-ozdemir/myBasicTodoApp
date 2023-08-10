package com.example.todoapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentNotGuncelleBinding
import com.example.todoapp.ui.viewmodel.NotGuncelleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotGuncelleFragment : Fragment() {

    private lateinit var binding: FragmentNotGuncelleBinding
    private lateinit var vm: NotGuncelleViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_not_guncelle, container, false)


        // gelen not ve içeriğia alicam VERİ ALMA İŞLEMİ
        val bundle: NotGuncelleFragmentArgs by navArgs()
        val gelenNote = bundle.notee




        binding.apply {
            notNesnesi = gelenNote //ÖNEMLİ  nesneyı aktardım
            notGuncelleFragment = this@NotGuncelleFragment //data bındıng ile baglantı saglandı
            notGuncelleToolbarTitle = "Guncelle"

        }

        return binding.root
    }

    fun fabGuncelle(not_baslik: String, not_icerik: String, view: View) {
        if (not_baslik.isNotEmpty() || not_icerik.isNotEmpty()) {
            vm.fabBtnGuncelle(not_baslik, not_icerik, view)
        } else {
            Toast.makeText(requireContext(), "Bos alanları doldur kenan", Toast.LENGTH_LONG).show()

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp: NotGuncelleViewModel by viewModels()  //bu bir delegate yapısı oldugu için direkt değer atayamıyorum
        this.vm = temp
    }

}

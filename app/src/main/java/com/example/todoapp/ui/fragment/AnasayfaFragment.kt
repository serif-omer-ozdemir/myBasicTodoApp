package com.example.todoapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentAnasayfaBinding
import com.example.todoapp.ui.adapter.NoteAdapter
import com.example.todoapp.ui.viewmodel.AnasayfaViewModel
import com.example.todoapp.util.gecisYap
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnasayfaFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var binding: FragmentAnasayfaBinding
    private lateinit var adapter: NoteAdapter
    private lateinit var vm: AnasayfaViewModel
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSingInClient: GoogleSignInClient

    //burada toolbar rv adapter ve fab buton data bındıng e aktarıldı

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_anasayfa, container, false)

        binding.apply {
            anasayfaFragment = this@AnasayfaFragment //databindiing e baglandı

            anasayfaToolbarTitle = "Note"
            (activity as AppCompatActivity).setSupportActionBar(toolbarAnasayfa)//diyorum kı sen bır actıon barsın her diger
            // sayfalarda arama özellığı gurunmcek diğer sayfaları kendı toolbar menusu var ise o ilgili menu gozukucek


            //  requireContext() fragment içinde direkt contex calısmaz bunu kullan

            vm.notListesi.observe(viewLifecycleOwner) {
                //burası lıve data ıle calısıcak
                // it olarak note listesini atadık
                adapter = NoteAdapter(requireContext(), it, vm)
                noteeAdapter = adapter//tasarima yolladım verileri
            }


        }

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.search_menu, menu)

                val item = menu.findItem(R.id.action_search)//arama işlemını yapan men item

                val sView = item.actionView as SearchView
                sView.setOnQueryTextListener(this@AnasayfaFragment) //boylece interfaaceye ekledik
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                //secilen menuItem a tılandıgında calısır ama ben arama işlemini searchviewOnqueryTextlistener  dan yapıcam bunu


                when (menuItem.itemId) {


                    R.id.actionLogOut -> {

                        auth.signOut()
                        Navigation.gecisYap(requireView(),R.id.action_anasayfaFragment_to_logInLogOutFragment)

                        Toast.makeText(requireContext(), "Cıkıs Yapıldı", Toast.LENGTH_LONG).show()







                    }
                }




                return false
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)


        return binding.root
    }

    fun fabNotEkle(it: View) {
        Navigation.gecisYap(it, R.id.action_anasayfaFragment_to_notKayitFragment)
    }


    //menu arama işlemleri
    override fun onQueryTextSubmit(query: String?): Boolean {
        vm.noteKelimeAra(query!!)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        vm.noteKelimeAra(newText!!)
        return true
    }

    override fun onResume() { //bu metodun olayı sayfa ilk acıldıgında ve  baska sayfaya gidip geri dondugumuzde tetıklenır
        super.onResume()
        //buraya arayuzu guncelleme ıle ılgıı kod yazıcam
        vm.tumNotlariYukle() //mesela not ekledıkten sonra geri don -dugumde saayfayı ynılıcek tum lısteyı bastan yuklıcek
        //bunu yapmazssam gerı dondugumde yenı bılgılerı goremem
    }

    override fun onCreate(savedInstanceState: Bundle?) { //ilk yaratildiğinda tetıklenıcek
        super.onCreate(savedInstanceState)
        val temp: AnasayfaViewModel by viewModels()  //bu bir delegate yapısı oldugu için direkt değer atayamıyorum
        this.vm = temp


        auth = FirebaseAuth.getInstance()

    }
}




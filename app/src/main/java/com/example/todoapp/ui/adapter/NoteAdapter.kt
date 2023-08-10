package com.example.todoapp.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.data.entity.Note
import com.example.todoapp.databinding.NoteItemBinding
import com.example.todoapp.ui.fragment.AnasayfaFragmentDirections
import com.example.todoapp.ui.viewmodel.AnasayfaViewModel
import com.example.todoapp.util.gecisYap
import com.google.android.material.snackbar.Snackbar

class NoteAdapter(
    private var mContext: Context,
    private var noteList: List<Note>,
    private var anasayfaViewModel: AnasayfaViewModel
) :
    RecyclerView.Adapter<NoteAdapter.CartTasarimTutucu>() {

    inner class CartTasarimTutucu(var gorselNesne: NoteItemBinding) :
        RecyclerView.ViewHolder(gorselNesne.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartTasarimTutucu {
        val lyinflater = LayoutInflater.from(mContext)//parent.context de olabilir
        val tsrm: NoteItemBinding =
            DataBindingUtil.inflate(lyinflater, R.layout.note_item, parent, false)
        return CartTasarimTutucu(tsrm)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: CartTasarimTutucu, position: Int) {

        val notee = noteList[position]

        holder.gorselNesne.noteNesnesi =
            notee // carttasarimdakı elemanlara data bındng yolu ıle erısıp atama yaptum

        holder.gorselNesne.apply {
            //baslık ,icerik, tarihin atması databındıng de yapıldi
            imageViewSil.setOnClickListener {
                Snackbar.make(it, "${notee.note_title} silinsin mi? ", Snackbar.LENGTH_LONG)
                    .setAction("Evet") {
                        anasayfaViewModel.NoteSil(notee.note_id)
                    }.show()
            }

            satirCartView.setOnClickListener {
                //burada bır veri gonderme işlemı gerceklesıcek
                //anasayfaKayit sinifindan gondercegım için AnasayfaFragmentDirection dan yapıcam
                val gecis =
                    AnasayfaFragmentDirections.actionAnasayfaFragmentToNotGuncelleFragment(notee)
                Navigation.gecisYap(satirCartView, gecis)

            }


        }

    }
}
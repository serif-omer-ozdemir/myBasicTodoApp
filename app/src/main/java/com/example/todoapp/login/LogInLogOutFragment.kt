package com.example.todoapp.login

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentLogInLogOutBinding
import com.example.todoapp.ui.viewmodel.LogInLogOutViewModel
import com.example.todoapp.util.gecisYap
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogInLogOutFragment : Fragment() {
    private lateinit var binding: FragmentLogInLogOutBinding
    private lateinit var vm: LogInLogOutViewModel
    private lateinit var auth: FirebaseAuth
    private lateinit var view: View
    private lateinit var googleSingInClient: GoogleSignInClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_log_in_log_out, container, false)

        binding.apply {
            loginLogoutFragmentt = this@LogInLogOutFragment //data binding baglandı

        }




        return binding.root
    }

    fun googleGiris(){
        signInWithGoogle()
    }


    fun butonGiris(eMail: String, password: String, view: View) {
        vm.girisButonuTikla(eMail, password, view)
    }

    fun buttonKayitOl(eMail: String, password: String, view: View) {
        vm.kaydetButonuTikla(eMail, password, view)
    }

    override fun onCreate(savedInstanceState: Bundle?) { //ilk yaratildiğinda tetıklenıcek
        super.onCreate(savedInstanceState)
        val temp: LogInLogOutViewModel by viewModels()  //bu bir delegate yapısı oldugu için direkt değer atayamıyorum
        this.vm = temp


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.id))
            .requestEmail()
            .build()

        googleSingInClient = GoogleSignIn.getClient(this.requireContext(), gso)


    }

    private fun signInWithGoogle() {
        val signInIntent = googleSingInClient.signInIntent
        launcher.launch(signInIntent)
    }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result ->

            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                handleResults(task)
            }
        }

    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful) {
            val account: GoogleSignInAccount? = task.result
            if (account != null) {
                updateUI(account)
            } else {
                Toast.makeText(
                    requireContext(),
                    "SignIn Failed, Try again later",
                    Toast.LENGTH_LONG
                ).show()
            }

        }
    }

    private fun updateUI(account:GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(requireContext(), "giris basarılı ", Toast.LENGTH_LONG).show()
                Navigation.gecisYap(view, R.id.action_logInLogOutFragment_to_anasayfaFragment)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Can't login currently. Try after sometime",
                    Toast.LENGTH_LONG).show()

            }
        }
    }








    //önemli FİRABEASE KULLANICI KAYITI OTOMATIK GİRİS
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.surekliAcikKal(view)
    }


}



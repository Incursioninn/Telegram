package com.example.tg_max.ui.fragments

import androidx.fragment.app.Fragment
import com.example.tg_max.MainActivity
import com.example.tg_max.R
import com.example.tg_max.activities.RegisterActivity
import com.example.tg_max.utilits.AUTH
import com.example.tg_max.utilits.AppTextWatcher
import com.example.tg_max.utilits.replaceActivity
import com.example.tg_max.utilits.showToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_enter_code.*


class EnterCodeFragment(val phoneNumber: String, val id: String) :
    Fragment(R.layout.fragment_enter_code) {



    override fun onStart() {
        super.onStart()
        (activity as RegisterActivity).title = phoneNumber
        register_input_code.addTextChangedListener(AppTextWatcher {

            val string = register_input_code.text.toString()
            if (string.length == 6) {
                certificateCode()
            }

        })
    }

    private fun certificateCode() {
        val code = register_input_code.text.toString()
        val credential = PhoneAuthProvider.getCredential(id ,code )
        AUTH.signInWithCredential(credential).addOnCompleteListener{
            if(it.isSuccessful){
                showToast("Добро пожаловать")
                (activity as RegisterActivity).replaceActivity(MainActivity())
            }
            else
                showToast(it.exception?.message.toString())
        }
    }

}
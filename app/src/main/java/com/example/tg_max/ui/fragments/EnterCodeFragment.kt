package com.example.tg_max.ui.fragments

import androidx.fragment.app.Fragment
import com.example.tg_max.MainActivity
import com.example.tg_max.R
import com.example.tg_max.activities.RegisterActivity
import com.example.tg_max.utilits.*
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_enter_code.*


class EnterCodeFragment(private val phoneNumber: String, val id: String) :
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
        val credential = PhoneAuthProvider.getCredential(id, code)
        AUTH.signInWithCredential(credential).addOnCompleteListener { taskCredential ->
            if (taskCredential.isSuccessful) {
                val uid = AUTH.currentUser?.uid.toString()
                val dateMap = mutableMapOf<String, Any>()
                dateMap[CHILD_ID] = uid
                dateMap[CHILD_PHONE] = phoneNumber
                dateMap[CHILD_USERNAME] = uid

                REF_DATABASE_ROOT.child(NODE_USERS).child(uid).updateChildren(dateMap)
                    .addOnCompleteListener { taskUpdateDatabase ->

                        if (taskUpdateDatabase.isSuccessful) {
                            showToast("Добро пожаловать")
                            (activity as RegisterActivity).replaceActivity(MainActivity())
                        } else showToast(taskUpdateDatabase.exception?.message.toString())


                    }

            } else
                showToast(taskCredential.exception?.message.toString())
        }
    }

}
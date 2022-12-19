package com.example.tg_max.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.tg_max.MainActivity
import com.example.tg_max.R
import com.example.tg_max.utilits.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_change_name.*
import kotlinx.android.synthetic.main.fragment_settings.*

class ChangeNameFragment : BaseChangeFragment(R.layout.fragment_change_name) {

    override fun onResume() {
        super.onResume()

        initFullNameList()


    }

    private fun initFullNameList() {
        val fullNameList = USER.fullName.split(" ")
        if (fullNameList.size > 1) {
            settings_input_name.setText(fullNameList[0])
            settings_input_surname.setText(fullNameList[1])
        } else settings_input_name.setText(fullNameList[0])
    }


    override fun change() {
        val name = settings_input_name.text.toString()
        val surname = settings_input_surname.text.toString()

        if (name.isEmpty()) {
            showToast(getString(R.string.settings_toast_name_is_empty))
        } else {
            val fullName = "$name $surname"
            REF_DATABASE_ROOT.child(NODE_USERS).child(UID).child(CHILD_FULLNAME)
                .setValue(fullName).addOnCompleteListener{

                if(it.isSuccessful){
                    showToast(getString(R.string.toast_data_updated))
                    USER.fullName = fullName
                    parentFragmentManager.popBackStack()
                }

            }



        }


    }


}
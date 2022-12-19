package com.example.tg_max.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.tg_max.MainActivity
import com.example.tg_max.R
import com.example.tg_max.ui.objects.AppValueEventListener
import com.example.tg_max.utilits.*
import kotlinx.android.synthetic.main.fragment_change_username.*
import java.util.*


class ChangeUsernameFragment : BaseChangeFragment(R.layout.fragment_change_username) {

    private lateinit var mNewUsername: String

    override fun onResume() {
        super.onResume()
        settings_input_username.setText(USER.username)

    }


    override fun change() {
        mNewUsername = settings_input_username.text.toString().lowercase(Locale.getDefault())
        if (mNewUsername.isEmpty())
            showToast("Имя пользователя пустое")
        else {
            REF_DATABASE_ROOT.child(NODE_USERNAMES)
                .addListenerForSingleValueEvent(AppValueEventListener { taskCheckUnique ->
                    if (taskCheckUnique.hasChild(mNewUsername))
                        showToast("Такой пользователь уже существует")
                    else {
                        REF_DATABASE_ROOT.child(NODE_USERNAMES).child(mNewUsername).setValue(UID)
                            .addOnCompleteListener {
                                if (it.isSuccessful) {
                                    updateCurrentUsername()
                                }
                            }
                    }
                })


        }
    }

    private fun updateCurrentUsername() {
        REF_DATABASE_ROOT.child(NODE_USERS).child(UID).child(CHILD_USERNAME).setValue(mNewUsername)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    deleteOldUsername()
                } else showToast(it.exception.toString())
            }
    }

    private fun deleteOldUsername() {
        REF_DATABASE_ROOT.child(NODE_USERNAMES).child(USER.username).removeValue()
            .addOnCompleteListener{
                if (it.isSuccessful) {
                    showToast(getString(R.string.toast_data_updated))
                    parentFragmentManager.popBackStack()
                    USER.username = mNewUsername
                } else showToast(it.exception.toString())
            }
    }

}
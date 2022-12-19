package com.example.tg_max.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.example.tg_max.R
import com.example.tg_max.utilits.*
import kotlinx.android.synthetic.main.fragment_change_bio.*

class ChangeBioFragment : BaseChangeFragment(R.layout.fragment_change_bio) {

    override fun onResume() {
        super.onResume()
        settings_input_bio.setText(USER.bio)
    }

    override fun change() {
        val newBio = settings_input_bio.text.toString()
        REF_DATABASE_ROOT.child(NODE_USERS).child(UID).child(CHILD_BIO).setValue(newBio).addOnCompleteListener{

            if(it.isSuccessful){
                showToast(getString(R.string.toast_data_updated))
                USER.bio = newBio
                parentFragmentManager.popBackStack()
            }

        }

    }

}
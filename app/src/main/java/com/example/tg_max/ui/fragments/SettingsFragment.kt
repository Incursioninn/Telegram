package com.example.tg_max.ui.fragments

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.canhub.cropper.CropImage
import com.example.tg_max.MainActivity
import com.example.tg_max.R
import com.example.tg_max.activities.RegisterActivity
import com.example.tg_max.utilits.AUTH
import com.example.tg_max.utilits.USER
import com.example.tg_max.utilits.replaceActivity
import com.example.tg_max.utilits.replaceFragment
import com.canhub.cropper.*
import kotlinx.android.synthetic.main.fragment_settings.*






class SettingsFragment : BaseFragment(R.layout.fragment_settings) {


    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
        initFields()
    }

    private fun initFields() {
        settings_about.text = USER.bio
        settings_full_name.text = USER.fullName
        settings_phone_number.text = USER.phone
        settings_status.text = USER.status
        settings_username.text = USER.username
        settings_btn_change_username.setOnClickListener {
            replaceFragment(ChangeUsernameFragment())
        }
        settings_btn_change_about.setOnClickListener {
            replaceFragment(ChangeBioFragment())
        }
        settings_change_photo.setOnClickListener {
            changePhotoUser()
        }
    }

    private fun changePhotoUser() {
        //TODO сделать изменение фотки пользователя
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.settings_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings_menu_exit -> {
                AUTH.signOut()
                (activity as MainActivity).replaceActivity(RegisterActivity())
            }
            R.id.settings_menu_change_name -> {
                replaceFragment(ChangeNameFragment())
            }


        }
        return true
    }

}
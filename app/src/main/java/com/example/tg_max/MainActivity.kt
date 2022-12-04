package com.example.tg_max

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.tg_max.activities.RegisterActivity
import com.example.tg_max.databinding.ActivityMainBinding
import com.example.tg_max.ui.fragments.ChatsFragment
import com.example.tg_max.ui.fragments.EnterPhoneNumberFragment
import com.example.tg_max.ui.objects.AppDrawer
import com.example.tg_max.utilits.AUTH
import com.example.tg_max.utilits.replaceActivity
import com.example.tg_max.utilits.replaceFragment
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mAppDrawer: AppDrawer
    private lateinit var mToolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    override fun onStart() {

        super.onStart()

        initFields()
        initFunc()

    }

    private fun initFunc() {
        if (AUTH.currentUser != null) {
            setSupportActionBar(mToolbar)
            mAppDrawer.create()
            replaceFragment(ChatsFragment() , false)
        } else {
            replaceActivity(RegisterActivity())
        }


    }


    private fun initFields() {
        mToolbar = mBinding.mainToolbar
        mAppDrawer = AppDrawer(this, mToolbar)
        AUTH = FirebaseAuth.getInstance()

    }
}
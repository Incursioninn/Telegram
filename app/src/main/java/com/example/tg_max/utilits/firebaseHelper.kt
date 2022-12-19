package com.example.tg_max.utilits

import com.example.tg_max.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

lateinit var AUTH : FirebaseAuth
lateinit var REF_DATABASE_ROOT : DatabaseReference

lateinit var USER : User
lateinit var UID : String


const val NODE_USERS = "Users"
const val NODE_USERNAMES = "UserNames"


const val CHILD_ID = "id"
const val CHILD_PHONE = "phone"
const val CHILD_USERNAME = "username"
const val CHILD_FULLNAME = "fullName"
const val CHILD_BIO = "bio"


fun initFirebase (){

    AUTH = FirebaseAuth.getInstance()
    REF_DATABASE_ROOT = FirebaseDatabase.getInstance().reference
    USER = User()
    UID = AUTH.currentUser?.uid.toString()
}
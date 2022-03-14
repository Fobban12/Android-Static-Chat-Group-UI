package com.example.mpntproject

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class LoginAndRegister: ViewModel() {
    var username = mutableStateOf("test")

    fun register (Email: String, Password: String)
    {
        Firebase.auth
            .createUserWithEmailAndPassword(Email, Password)
            .addOnSuccessListener { username.value = Email }
    }

    fun login (Email: String, Password: String){
        Firebase.auth
            .signInWithEmailAndPassword(Email, Password)
            .addOnSuccessListener { username.value = Email }

    }
    fun logout(){Firebase.auth.signOut()
        username.value = ""}


}
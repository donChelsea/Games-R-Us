package com.example.games_r_us

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

object FirebaseUtils {

    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    val database = FirebaseDatabase.getInstance()

}
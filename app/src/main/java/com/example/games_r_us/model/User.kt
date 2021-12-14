package com.example.games_r_us.model

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

data class User (
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
) : Serializable

@IgnoreExtraProperties
data class UserData (
    var id: String? = null,
    var orderHistory: List<Game>? = null,
    var wishList: List<Game>? = null,
    var wallet: List<PaymentCard>? = null
) : Serializable


data class PaymentCard(
    val cardNum: String,
    val name: String,
    val image: String
)
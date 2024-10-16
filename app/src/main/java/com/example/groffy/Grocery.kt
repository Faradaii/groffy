package com.example.groffy

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Grocery(
    val name: String,
    val description: String,
    val photo: String,
    val price: Int,
    val nutrients: String,
    val processingBenefits: String
) : Parcelable

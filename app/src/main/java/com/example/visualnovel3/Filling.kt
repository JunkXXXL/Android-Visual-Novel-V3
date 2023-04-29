package com.example.visualnovel3

import kotlinx.serialization.Serializable

@Serializable
data class  Filling(
    val dialog: String,
    val firstButton: String,
    val secondButton: String,
    val thirdButton: String,
    val textField: Boolean,
    val firstButtonNext: Int,
    val secondButtonNext: Int,
    val thirdButtonNext: Int,
    val fon: String
)

@Serializable
data class Scenes(
    val scene: List<Filling>
)
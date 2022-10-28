package com.yotta.demo.model

data class PayloadWeight(
    val id: String,
    val kg: Int,
    val lb: Int,
    val name: String
): java.io.Serializable
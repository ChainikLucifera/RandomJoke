package com.example.randomjoke.model

import com.google.gson.annotations.SerializedName

data class Joke(
    @SerializedName("joke")
    var joke: String
)
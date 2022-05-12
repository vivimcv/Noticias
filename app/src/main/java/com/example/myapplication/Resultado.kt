package com.example.myapplication

import com.google.gson.annotations.SerializedName

data class Resultado(

    @SerializedName("title") var title: String,
    @SerializedName("url") var url:String,
    @SerializedName("published_date") var published_date:String,
    @SerializedName("media") var media:List<Media>

    )

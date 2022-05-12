package com.example.myapplication

import com.google.gson.annotations.SerializedName

data class Media(
    @SerializedName("media-metadata") val mediaMetadata:List<MediaData>
)

data class MediaData(
    @SerializedName("url") val url:String
)

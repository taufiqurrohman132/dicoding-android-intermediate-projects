package com.dicoding.picodiploma.mycamera.data.api

import android.os.Message
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializer

data class FileUploadResponse(
    @field:SerializedName("error")
    val error: Boolean,
    @field:SerializedName("message")
    val message: String
)

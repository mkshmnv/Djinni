package com.mkshmnv.djinni.network

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class AnswerModel(
    @SerializedName(CUSTOM_KEY) val keyWeb: String?,
    @SerializedName(PUSH_KEY) val push: String?,
    @SerializedName(CUSTOM_RESPONSE_KEY) val keyGame: String?
) {
    companion object {
        const val CUSTOM_KEY = "url_aviatorx"
        const val CUSTOM_RESPONSE_KEY = "CanPlay_aviatorx"
        const val PUSH_KEY = "push"
    }
}
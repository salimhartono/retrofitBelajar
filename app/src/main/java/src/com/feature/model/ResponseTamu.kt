package src.com.feature.model

import com.google.gson.annotations.SerializedName
import src.com.feature.tamu.respons.ResponseTamu

data class ResponseTamu(
    @SerializedName("result_tamu")
    val tamu: List<ResponseTamu>
)
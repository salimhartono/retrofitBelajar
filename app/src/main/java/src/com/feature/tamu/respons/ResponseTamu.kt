package src.com.feature.tamu.respons

import com.google.gson.annotations.SerializedName

data class ResponseTamu(

    @field:SerializedName("tamuPhone")
    val tamuPhone: String? = null,

    @field:SerializedName("idTamu")
    val idTamu: String? = null,

    @field:SerializedName("namaSapaan")
    val namaSapaan: String? = null,

    @field:SerializedName("linkFoto")
    val linkFoto: String? = null,

    @field:SerializedName("namaLengkap")
    val namaLengkap: String? = null,

    @field:SerializedName("levelTamu")
    val levelTamu: String? = null,

    @field:SerializedName("alamat")
    val alamat: String? = null,

    @field:SerializedName("statusTamu")
    val statusTamu: String? = null
)
package src.com.feature.model

import com.google.gson.annotations.SerializedName

data class ModelTamu(
    @SerializedName("idTamu") val idTamu: String?,
    @SerializedName("namaSapaan") val namaSapaan: String?,
    @SerializedName("namaLengkap") val namaLengkap: String?,
    @SerializedName("alamat") val alamat : String?,
    @SerializedName("tamuPhone") val  tamuPhone: String?,
    @SerializedName("linkFoto") val fotoTamu: String?,
    @SerializedName("levelTamu") val levelTamu: String?,
    @SerializedName("statusTamu") val statusTamu: String?
)
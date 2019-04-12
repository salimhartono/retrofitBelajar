package src.com.feature.api.retrofit

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Multipart
import retrofit2.http.Part
import retrofit2.http.GET
import src.com.feature.tamu.respons.ResponseTamu


interface BaseApiService {
    @Multipart
    @POST("tamu_insert.php")
    fun updateGambar(
        @Part("namaSapaan") namaSapaan: RequestBody,
        @Part("namaLengkap") namaLengkap: RequestBody,
        @Part("tamuPhone") tamuPhone: RequestBody,
        @Part("alamat") alamat: RequestBody,
        @Part("linkFoto") linkFoto: RequestBody,
        @Part("statusTamu") statusTamu: RequestBody,
        @Part("levelTamu") levelTamu: RequestBody,
        @Part image: MultipartBody.Part
    ): Call<ResponseBody>

    @GET("tamu_list.php")
    fun getAllTamu(): Call<ResponseTamu>

    @GET("tamu_list.php")
    fun getListTamu():Call<ArrayList<ResponseTamu>>

}
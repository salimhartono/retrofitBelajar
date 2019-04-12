package src.com.feature.manual_book

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.activity_manual_book.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.jetbrains.anko.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import src.com.feature.R
import src.com.feature.api.retrofit.BaseApiService
import src.com.feature.api.retrofit.ServiceAPI
import src.com.feature.utils.invisible
import src.com.feature.utils.visible
import java.io.File


class ManualBook : AppCompatActivity(), View.OnClickListener {

    private lateinit var apiService: BaseApiService
    private lateinit var idTamu: String
    private var statusImage = 1
    private var statusNama = 1
    private var statusAlamat = 1
    private lateinit var uri: Uri
    private val STORAGE_REQUEST_CODE = 99
    private lateinit var loading: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manual_book)
        setupPermission()

        ivChoose.setOnClickListener(this)
        btConfirm.setOnClickListener(this)

        apiService = ServiceAPI.getAPIService()
        checkStatus()

        alert(getString(R.string.titleManualBook), "hallo") {
            yesButton {
            }
        }.show()

        Glide.with(this)
            .load(getString(R.string.tamuDasboard))
            .into(ivTamusDashboard)


    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.ivChoose -> {
                if (statusImage == 2) {
                    statusImage = 1
                    ivCircle.setImageResource(R.drawable.ic_add_a_photo_black_24dp)
                    tvUpload.text = getString(R.string.uploadImage)
                    vUpload.setBackgroundColor(resources.getColor(R.color.blueLight))
                } else {
                    choseImage()
                }
            }

            R.id.btConfirm -> {
                if (statusImage == 2 && statusNama == 2 && statusAlamat == 2) {
                    //toast("gambar segera di upload")
                    uploadImage()
                } else {
                    if (statusImage != 2 && statusNama != 2 && statusAlamat != 2) {
                        toast("Sory Sob, Kamu belum isi apa apa tuh. di isi dulu yah")

                    } else if (statusImage != 2 && statusNama != 2) {
                        toast("Foto sama Nama kamu kosong, moggo di isi dulu yah..")

                    } else if (statusImage != 2 && statusAlamat != 2) {
                        toast("Foto sama Alamat kamu kosong, moggo di isi dulu yah..")

                    } else if (statusNama != 2 && statusAlamat != 2) {
                        toast("Nama sama Alamat kamu kosong, moggo di isi dulu yah..")

                    }else if (statusImage != 2) {
                        toast("Sory Sob, Gambarmu tidak boleh kosong")

                    } else if (statusNama != 2) {
                        toast("Sory Sob, kamu gak boleh kosongin kolom nama")

                    } else if (statusAlamat != 2) {
                        toast("Alamat mu gak boleh kosong")

                    }
                }
            }
        }
    }

    private fun choseImage() {
        CropImage.activity()
            .setGuidelines(CropImageView.Guidelines.ON)
            .setAspectRatio(5, 5)
            .setFixAspectRatio(true)
            .setCropShape(CropImageView.CropShape.RECTANGLE)
            .start(this)
    }

    private fun uploadImage() {
        loading = ProgressDialog.show(this, null, "Loading...", false, false)
        val imageFile = File(uri.path)
        toast(imageFile.toString())

        val requestId = RequestBody.create(MediaType.parse("*/*"), imageFile)
        val fileUpload = MultipartBody.Part.createFormData("users_images", imageFile.name, requestId)
        val namaSapaan = RequestBody.create(MediaType.parse("*/*"), etNamaTamu.text.toString())
        val namaLengkap = RequestBody.create(MediaType.parse("*/*"), etNamaTamu.text.toString())
        val tamuPhone = RequestBody.create(MediaType.parse("*/*"), etPhone.text.toString())
        val alamat = RequestBody.create(MediaType.parse("*/*"), etAlamat.text.toString())
        val statusTamu = RequestBody.create(MediaType.parse("*/*"), "1")
        val levelTamu = RequestBody.create(MediaType.parse("*/*"), "1")
        apiService.updateGambar(requestId, namaSapaan, namaLengkap, tamuPhone, alamat, statusTamu, levelTamu, fileUpload)
            .enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    Log.d("RETRO", "ON RESPONSE  : " + response.body().toString());
                    if (response.isSuccessful) {
                        loading.dismiss()
                        toast("Berhasil Menambahkan Teman")
                    } else {
                        loading.dismiss()
                        toast("gagal gaes")
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    loading.dismiss()
                    t.printStackTrace()
                }

            })
    }

    private fun checkStatus() {
        etNamaTamu.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                var leng = etNamaTamu.text.toString()
                if (leng.length > 2) {
                    statusNama = 2
                    if (statusImage == 2 && statusAlamat == 2) {
                        //btActive.visible()
                    }
                } else {
                    statusNama = 1
                    //btActive.invisible()
                }
            }

        })

        etAlamat.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                var leng = etAlamat.text.toString()
                if (leng.length > 4) {
                    statusAlamat = 2
                    if (statusImage == 2 && statusNama == 2) {
                        btActive.visible()
                    }
                } else {
                    statusAlamat = 1
                    btActive.invisible()
                }
            }

        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == Activity.RESULT_OK) {
                tvUpload.text = getString(R.string.deleteImage)
                vUpload.setBackgroundColor(resources.getColor(R.color.orange))
                statusImage = 2
                uri = result.uri
                Glide.with(this)
                    .load(uri)
                    .into(ivCircle)
                if (statusNama == 2 && statusAlamat == 2) {
                    btActive.visible()
                }
            }
        }
    }

    private fun setupPermission() {
        val permission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
        if (permission != PackageManager.PERMISSION_GRANTED) {
            toast("akses tidak diberikan")
            makeRequest()
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(
            this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            STORAGE_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            STORAGE_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    toast("Permission has been denied by user")
                } else {
                    toast("Permission has been granted by user")
                }
            }
        }
    }
}

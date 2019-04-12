package src.com.feature.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast
import src.com.feature.R
import src.com.feature.R.layout.activity_home
import src.com.feature.api.ApiRepository
import src.com.feature.model.ModelTamu
import src.com.feature.tamu.AdapterTamu
import src.com.feature.tamu.PresenterTamu
import src.com.feature.tamu.ViewTamu

class Home : AppCompatActivity() {

    private var tamus: MutableList<ModelTamu> = mutableListOf()
    //private lateinit var presenterTamu: PresenterTamu
    private lateinit var adapterTamu: AdapterHome
    private lateinit var daftarTamu: String

    private lateinit var typeLogin: String
    private lateinit var name: String
    private lateinit var email: String
    private lateinit var foto: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_home)

        typeLogin = intent.getStringExtra("typeLogin")
        name = intent.getStringExtra("namaTamu")
        email = intent.getStringExtra("email")
        foto = intent.getStringExtra("photo")

        daftarTamu = "133604"
        loadAcara()

        tvNamaTamu.text = name
        tvEmailTamu.text = email

        //foto profile user
        Glide.with(this)
            .load(foto)
            .apply(RequestOptions().circleCrop())
            .into(ivTamu)

        //foto bg frame
        Glide.with(this)
            .load(getString(R.string.mainFrame))
            .into(ivHomeFrame)

        //bg list
        Glide.with(this)
            .load(getString(R.string.tamuDasboard))
            .into(ivHomeHeader)

        lyScan.onClick {
            toast("coming soon")
        }

        ivTamu.onClick {
            toast("halo $name")
        }

    }

    fun loadAcara() {

    }


}

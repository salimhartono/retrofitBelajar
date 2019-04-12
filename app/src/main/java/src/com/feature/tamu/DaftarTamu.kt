package src.com.feature.tamu

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.View
import android.view.animation.AnimationUtils
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_daftar_tamu.*
import org.jetbrains.anko.support.v4.onRefresh
import src.com.feature.R
import src.com.feature.R.layout.activity_daftar_tamu
import src.com.feature.api.ApiRepository
import src.com.feature.api.retrofit.BaseApiService
import src.com.feature.api.retrofit.ServiceAPI
import src.com.feature.tamu.respons.ResponseTamu
import src.com.feature.utils.invisible


class DaftarTamu : AppCompatActivity(), ViewTamu, View.OnClickListener {


    private var tamus: MutableList<ResponseTamu> = mutableListOf()
    private lateinit var adapterTamu: AdapterTamu
    private lateinit var daftarTamu: String
    private lateinit var apiService: BaseApiService
    private lateinit var presenterTamu: PresenterTamu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_daftar_tamu)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        tbDaftarTamu.inflateMenu(R.menu.menu_search)

        apiService = ServiceAPI.getAPIService()

        daftarTamu = "133604"
        closeNotif.setOnClickListener(this)

        loadData()
        swipeTamu.onRefresh {
            presenterTamu.getTamuList()
        }

    }

    override fun onClick(p0: View?) {

        when (p0?.id) {
            R.id.closeNotif -> {
                val animation = AnimationUtils.loadAnimation(applicationContext, R.anim.abc_fade_out)
                layoutNotif.startAnimation(animation)
                layoutNotif.invisible()
            }
        }
    }

    private fun loadData() {
        val requesApi = ApiRepository()
        val gson = Gson()
        val presenterTamu = PresenterTamu(this, requesApi, gson)

        adapterTamu = AdapterTamu(tamus)
        listTamu.layoutManager = LinearLayoutManager(this)
        listTamu.adapter = adapterTamu
        presenterTamu.getTamuList()
    }

    override fun showLoading() {
        swipeTamu.isRefreshing = true
    }

    override fun hideLodaing() {
        swipeTamu.isRefreshing = true
    }

    override fun showTamuList(tamu: List<ResponseTamu>) {
        tamus.clear()
        tamus.addAll(tamu)
        adapterTamu.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        return true
    }
}

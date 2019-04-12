package src.com.feature.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.raw_layout_home.view.*
import src.com.feature.R
import src.com.feature.model.ModelTamu
import src.com.feature.model.ResponseTamu
import src.com.feature.tamu.TamuHolder

class AdapterHome(
        private val homes: List<ResponseTamu>,
        private val listener: (ResponseTamu) -> Unit
) : RecyclerView.Adapter<HomeHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): HomeHolder {
        return HomeHolder(
                LayoutInflater.from(p0.context).inflate(R.layout.raw_layout_home, p0, false)
        )
    }

    override fun getItemCount(): Int = homes.size

    override fun onBindViewHolder(p0: HomeHolder, p1: Int) {
        p0.bindItem(homes[p1], listener)
    }

}

class HomeHolder(view:View): RecyclerView.ViewHolder(view){
    fun bindItem(tamu: ResponseTamu, listener: (ResponseTamu) -> Unit){
        Glide.with(itemView.context).load(tamu).into(itemView.ivHomeCard)
        itemView.setOnClickListener { listener(tamu) }
    }
}
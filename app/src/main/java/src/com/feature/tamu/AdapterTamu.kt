package src.com.feature.tamu

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.raw_layout_tamu.view.*
import src.com.feature.R
import src.com.feature.model.ModelTamu
import src.com.feature.tamu.respons.ResponseTamu

class AdapterTamu(private val tamus: MutableList<ResponseTamu>) : RecyclerView.Adapter<TamuHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TamuHolder {
        return TamuHolder(
            LayoutInflater.from(p0.context).inflate(
                R.layout.raw_layout_tamu, p0, false
            )
        )
    }

    override fun getItemCount(): Int = tamus.size

    override fun onBindViewHolder(p0: TamuHolder, p1: Int) {
        p0.bindItem(tamus[p1])
    }

}

class TamuHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bindItem(tamu: ResponseTamu) {
        Glide.with(itemView.context).load(tamu.linkFoto).into(itemView.raw_image)
        itemView.rawIdTamu.text = tamu.idTamu
        itemView.rawNamaTamu.text = tamu.namaLengkap
        itemView.rawAlamatTamu.text = tamu.alamat

//        itemView.setOnClickListener { listener(tamu) }
    }

}
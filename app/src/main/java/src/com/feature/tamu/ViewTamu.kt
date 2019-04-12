package src.com.feature.tamu

import src.com.feature.model.ModelTamu
import src.com.feature.tamu.respons.ResponseTamu

interface ViewTamu {
    fun showLoading()
    fun hideLodaing()
    fun showTamuList(tamu: List<ResponseTamu>)
}
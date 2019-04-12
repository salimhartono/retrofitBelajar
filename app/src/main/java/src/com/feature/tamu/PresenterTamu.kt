package src.com.feature.tamu

import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import src.com.feature.api.ApiRepository
import src.com.feature.api.TamuDB
import src.com.feature.model.ResponseTamu
import src.com.feature.utils.CorourineContexProvider
import kotlin.coroutines.CoroutineContext

class PresenterTamu(
    private val view: ViewTamu,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CorourineContexProvider = CorourineContexProvider()
){
    fun getTamuList(){
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequest(TamuDB.getDAftarTamu()).await(),
                ResponseTamu::class.java
            )
            view.hideLodaing()
            view.showTamuList(data.tamu)
        }
    }

}
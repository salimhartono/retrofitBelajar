package src.com.feature.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

open class CorourineContexProvider{
    open val main: CoroutineContext by lazy {
        Dispatchers.Main
    }
}
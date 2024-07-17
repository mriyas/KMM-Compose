package network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <T> toResultFlow(call: suspend () -> T): Flow<NetWorkResult<T>> {
    return flow {
        emit(NetWorkResult.Loading(true))
        try {
            val c = call.invoke()
            c.let { response ->

                println("response${response}")
                emit(NetWorkResult.Success(response))
            }
        } catch (e: Exception) {
            emit(NetWorkResult.Error(null, e.toString()))
        }
    }
}

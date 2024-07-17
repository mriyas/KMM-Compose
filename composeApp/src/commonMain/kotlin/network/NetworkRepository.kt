package network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import models.ApiResponse

class NetworkRepository(private val httpClient: HttpClient) {

    fun getProducts(): Flow<NetWorkResult<ApiResponse?>> {
        return toResultFlow {
            httpClient.get("https://dummyjson.com/products").body<ApiResponse>()
        }
    }
}
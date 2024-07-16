package network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import models.ApiResponse

class NetworkRepository(private val httpClient: HttpClient) {

     fun getRecipes(): Flow<NetWorkResult<ApiResponse?>> {
        return toResultFlow {
                val response = httpClient.get("https://dummyjson.com/recipes").body<ApiResponse>()
                 NetWorkResult.Success(response)
        }
    }


    private suspend inline fun <reified T> getRecipes(): T  {
        return httpClient.get("https://dummyjson.com/recipes").body<T>()
    }
}
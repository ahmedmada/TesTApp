package com.swensonhe.test.api

import com.swensonhe.test.model.Data
import com.swensonhe.test.utils.NetworkConstants.URL_PATH
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(URL_PATH)
    suspend fun getData(): Response<Data>
}

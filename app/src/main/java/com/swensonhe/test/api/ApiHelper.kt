package com.swensonhe.test.api

import com.swensonhe.test.model.Data
import retrofit2.Response

interface ApiHelper {
    suspend fun getData(): Response<Data>
}

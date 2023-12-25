package com.swensonhe.test.api

import com.swensonhe.test.model.Data
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService
) : ApiHelper {
    override suspend fun getData(): Response<Data> = apiService.getData()
}

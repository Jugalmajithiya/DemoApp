package com.example.demoapplication.data.repo



import com.example.demoapplication.data.network.ApiInterface
import com.example.demoapplication.data.network.SafeApiRequest


class UserRepository(
    private val apiInterface: ApiInterface
) : SafeApiRequest() {

    suspend fun getProductData() =
        apiInterface.getDataRequest()
}
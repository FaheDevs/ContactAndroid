package com.zidani.contact

import UsersItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("/users")
    fun getPosts(): Call<List<UsersItem>>
}

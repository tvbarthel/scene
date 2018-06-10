package fr.tvbarthel.scene.http

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface SceneService {

    @POST("photo/upload")
    fun updateUser(@Body file: RequestBody): Call<ResponseBody>

}
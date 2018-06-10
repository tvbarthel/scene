package fr.tvbarthel.scene.http

import android.util.Log
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.io.File


/**
 * Used to encapsulate every communication with the Scene server.
 */
class SceneClient {

    private var sceneService: SceneService? = null

    fun send(file: File, scene: String) {
        initService(scene)
        val reqFile = RequestBody.create(MediaType.parse("image/*"), file)
        val update = sceneService?.updateUser(reqFile)
        update?.enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                Log.d("LARGONNE onFailure", t?.message)
            }

            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                Log.d("LARGONNE onResponse", response?.message())
            }


        })

    }

    private fun initService(scene: String) {
        if (sceneService == null) {
            sceneService = Retrofit.Builder().baseUrl(scene).build().create(SceneService::class.java)
        }
    }
}
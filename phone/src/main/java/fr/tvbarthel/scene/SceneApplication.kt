package fr.tvbarthel.scene

import android.app.Application

class SceneApplication : Application() {


    companion object {
        //platformStatic allow access it from java code
        @JvmStatic
        lateinit var graph: SceneComponent
    }

    override fun onCreate() {
        super.onCreate()
        graph = DaggerSceneComponent.builder().applicationModule(ApplicationModule(this)).build()
    }
}
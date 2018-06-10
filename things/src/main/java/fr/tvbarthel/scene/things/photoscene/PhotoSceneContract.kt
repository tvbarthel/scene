package fr.tvbarthel.scene.things.photoscene

import java.io.File

class PhotoSceneContract {

    interface Screen {
        fun showIpAddress(ip: String)
        fun showPhoto(photo: File)
    }

    interface Interactor {
        fun attachScreen(screen: Screen)
        fun detachScreen()
        fun destroy()
    }
}
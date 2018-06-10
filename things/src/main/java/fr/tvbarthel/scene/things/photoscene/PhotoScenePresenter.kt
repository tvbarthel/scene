package fr.tvbarthel.scene.things.photoscene

import fr.tvbarthel.scene.things.file.FileManager
import fr.tvbarthel.scene.things.server.PhotoUploadServerManager
import java.io.File

class PhotoScenePresenter(fileManager: FileManager) : PhotoSceneContract.Interactor {

    private val server: PhotoUploadServerManager
    private var latestPhoto: File? = null
    private var screen: PhotoSceneContract.Screen? = null

    init {
        server = PhotoUploadServerManager(fileManager, 5000, { photo ->
            latestPhoto = photo
            screen?.showPhoto(photo)
        })

        server.start(10_000)
    }

    override fun attachScreen(screen: PhotoSceneContract.Screen) {
        this.screen = screen

        screen.showIpAddress("192.168.1.81")
        if (latestPhoto != null) {
            screen.showPhoto(latestPhoto!!)
        }
    }

    override fun detachScreen() {
        screen = null
    }

    override fun destroy() {
        server.stop()
    }
}
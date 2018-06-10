package fr.tvbarthel.scene.things.photoscene

import fr.tvbarthel.scene.things.file.FileManager
import fr.tvbarthel.scene.things.server.PhotoUploadServerManager
import fr.tvbarthel.scene.things.wifi.IpResolver
import java.io.File

class PhotoScenePresenter(fileManager: FileManager,
                          private val ipResolver: IpResolver)
    : PhotoSceneContract.Interactor {

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

        val idAddress = ipResolver.resolveIpAddress()
        screen.showIpAddress(idAddress)

        latestPhoto?.let {
            screen.showPhoto(it)
        }
    }

    override fun detachScreen() {
        screen = null
    }

    override fun destroy() {
        server.stop()
    }
}
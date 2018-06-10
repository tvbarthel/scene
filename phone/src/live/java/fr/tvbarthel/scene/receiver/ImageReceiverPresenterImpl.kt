package fr.tvbarthel.scene.receiver

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.CountDownTimer
import fr.tvbarthel.scene.http.SceneClient
import fr.tvbarthel.scene.storage.StorageManager
import java.io.File

class ImageReceiverPresenterImpl(var storage: StorageManager, var client: SceneClient) : ImageReceiverContract.Presenter {

    private var view: ImageReceiverContract.View? = null
    private var countDownTimer: CountDownTimer? = null
    private var photo: String? = null

    override fun attach(view: ImageReceiverContract.View) {
        this.view = view
    }

    override fun detach(view: ImageReceiverContract.View) {
        this.view = null
        countDownTimer?.cancel()
    }

    override fun prepareImage(intent: Intent) {
        val uri: Uri = intent.getParcelableExtra(Intent.EXTRA_STREAM)
        view?.showLoading()
        photo = storage.storeImage(uri)
        view?.showPreview(BitmapFactory.decodeFile(photo))
        view?.showSend()
    }

    override fun sendImage(scene: String) {
        if (photo == null) {
            view?.showError("No photo to send")
            return
        }

        view?.showLoading()
        storage.storeScene(scene)
        client.send(File(photo), scene)
        view?.showSuccess()

    }

    override fun lastScene(): String? = storage.loadScene()
}
package fr.tvbarthel.scene.receiver

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.CountDownTimer
import fr.tvbarthel.scene.storage.StorageManager

class ImageReceiverPresenterImpl(var storage: StorageManager) : ImageReceiverContract.Presenter {

    private var view: ImageReceiverContract.View? = null
    private var countDownTimer: CountDownTimer? = null

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
        val file = storage.storeImage(uri)
        view?.showPreview(BitmapFactory.decodeFile(file))
        view?.showSend()
    }

    override fun sendImage(scene: String) {
        view?.showLoading()

        storage.storeScene(scene)

        countDownTimer = object : CountDownTimer(1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                view?.showError("No implemented")
            }
        }
        countDownTimer?.start()

    }

    override fun lastScene(): String? = storage.loadScene()
}
package fr.tvbarthel.scene.receiver

import android.net.Uri
import android.os.CountDownTimer

class ImageReceiverPresenterImpl : ImageReceiverContract.Presenter {

    private var view: ImageReceiverContract.View? = null
    private var countDownTimer: CountDownTimer? = null

    override fun attach(view: ImageReceiverContract.View) {
        this.view = view
    }

    override fun detach(view: ImageReceiverContract.View) {
        this.view = null
        countDownTimer?.cancel()
    }

    override fun send(uri: Uri, scene: String) {
        view?.showLoading()

        countDownTimer = object : CountDownTimer(1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                view?.showError("No implemented")
            }
        }
        countDownTimer?.start()

    }

    override fun lastScene(): String? = "192.168.0.1:400"
}
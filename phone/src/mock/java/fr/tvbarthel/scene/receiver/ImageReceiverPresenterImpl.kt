package fr.tvbarthel.scene.receiver

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.CountDownTimer
import fr.tvbarthel.scene.R

class ImageReceiverPresenterImpl(var context: Context) : ImageReceiverContract.Presenter {

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
        view?.showLoading()

        countDownTimer = object : CountDownTimer(2500, 2500) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                view?.showPreview(BitmapFactory.decodeResource(context.resources, R.drawable.test))
                view?.showSend()
            }
        }
        countDownTimer?.start()
    }

    override fun sendImage(scene: String) {
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
package fr.tvbarthel.scene.receiver

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import fr.tvbarthel.scene.R
import fr.tvbarthel.scene.SceneApplication
import kotlinx.android.synthetic.main.activity_image_receiver.*
import javax.inject.Inject

/**
 * Activity launched by another app once the user a selection the app inside the share picker.
 */
class ImageReceiverActivity : AppCompatActivity(), ImageReceiverContract.View {

    @Inject
    lateinit var presenter: ImageReceiverContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SceneApplication.graph.inject(this)
        setContentView(R.layout.activity_image_receiver)

        activity_image_receiver_send_button.setOnClickListener(this::onSendImageRequested)

        activity_image_receiver_ip_edit.setText(presenter.lastScene() ?: "")
    }

    override fun onResume() {
        super.onResume()
        presenter.attach(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.detach(this)
    }

    override fun showLoading() {
        activity_image_receiver_loading.visibility = View.VISIBLE
        activity_image_receiver_send_button.visibility = View.INVISIBLE
    }

    override fun showError(error: String) {
        activity_image_receiver_loading.visibility = View.INVISIBLE
        activity_image_receiver_send_button.visibility = View.VISIBLE
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun showSuccess() {
        activity_image_receiver_loading.visibility = View.INVISIBLE
        activity_image_receiver_send_button.visibility = View.VISIBLE
        Toast.makeText(this, R.string.success, Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun onSendImageRequested(view: View) {
        presenter.send(
                intent.getParcelableExtra(Intent.EXTRA_STREAM),
                activity_image_receiver_ip_edit.text.toString()
        )
    }

}
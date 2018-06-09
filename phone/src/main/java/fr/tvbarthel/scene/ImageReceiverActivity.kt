package fr.tvbarthel.scene

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_image_receiver.*
import javax.inject.Inject

/**
 * Activity launched by another app once the user a selection the app inside the share picker.
 */
class ImageReceiverActivity : AppCompatActivity() {

    @Inject
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SceneApplication.graph.inject(this)
        setContentView(R.layout.activity_image_receiver)
        activity_image_receiver_send_button.setOnClickListener(this::onSendImageRequested)
    }

    override fun onResume() {
        super.onResume()
        intent?.let {
            Log.d("SharedImageReceiver", intent.toString())
        }
    }

    private fun onSendImageRequested(view: View) {
        val text = "send photo to scene ${activity_image_receiver_ip_edit.text}"
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

}
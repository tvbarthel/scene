package fr.tvbarthel.scene.things

import android.app.Activity
import android.os.Bundle
import com.squareup.picasso.Picasso
import fr.tvbarthel.scene.things.file.FileManager
import fr.tvbarthel.scene.things.photoscene.PhotoSceneContract
import fr.tvbarthel.scene.things.photoscene.PhotoScenePresenter
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : Activity(), PhotoSceneContract.Screen {

    private lateinit var interactor: PhotoSceneContract.Interactor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fileManager = FileManager(applicationContext)
        interactor = PhotoScenePresenter(fileManager)
    }

    override fun onStart() {
        super.onStart()
        interactor.attachScreen(this)
    }

    override fun onStop() {
        super.onStop()
        interactor.detachScreen()
    }

    override fun onDestroy() {
        super.onDestroy()
        interactor.destroy()
    }

    override fun showIpAddress(ip: String) {
        val ipText = getString(R.string.main_ip_address_template, ip)
        mainIpAddressTextView.text = ipText
    }

    override fun showPhoto(photo: File) {
        runOnUiThread {
            Picasso.get().invalidate(photo)
            Picasso.get().load(photo).into(mainImageView)
        }
    }
}

package fr.tvbarthel.scene.things

import android.app.Activity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    companion object {
        const val IMAGE_URL = "https://cdn.pixabay.com/photo/2017/12/30/08/41/panoramic-3049543_960_720.jpg"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Picasso.get()
                .load(IMAGE_URL)
                .into(mainImageView)
    }
}

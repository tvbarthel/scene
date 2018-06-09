package fr.tvbarthel.scene

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Main activity used to welcome the user and explain how to use scene.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

package fr.tvbarthel.scene

import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ImageSpan
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Main activity used to welcome the user and explain how to use scene.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activity_main_instruction.text = buildInstruction()
    }

    private fun buildInstruction(): SpannableStringBuilder {
        val builder = SpannableStringBuilder()

        val instruction = getString(R.string.instruction)
        val shareIconPlaceholder = getString(R.string.instruction_icon_share)
        val sceneIconPlaceholder = getString(R.string.instruction_icon_scene)
        val iconSize = resources.getDimensionPixelSize(R.dimen.instruction_icon_size)

        builder.append(instruction)

        val shareIcon = ContextCompat.getDrawable(this, R.drawable.ic_share_white_24dp)!!.mutate()
        shareIcon.setBounds(0, 0, iconSize, iconSize)
        shareIcon.setColorFilter(ContextCompat.getColor(this, R.color.instruction_share_icon), PorterDuff.Mode.SRC_IN)
        var start = instruction.indexOf(shareIconPlaceholder)
        var end = start + shareIconPlaceholder.length
        val shareIconSpan = ImageSpan(shareIcon, ImageSpan.ALIGN_BOTTOM)
        builder.setSpan(shareIconSpan, start, end, 0)

        val sceneIcon = ContextCompat.getDrawable(this, R.drawable.ic_scene)!!.mutate()
        sceneIcon.setColorFilter(ContextCompat.getColor(this, R.color.instruction_scene_icon), PorterDuff.Mode.SRC_IN)
        sceneIcon.setBounds(0, 0, iconSize, iconSize)
        start = instruction.indexOf(sceneIconPlaceholder)
        end = start + sceneIconPlaceholder.length
        val sceneIconSpan = ImageSpan(sceneIcon, ImageSpan.ALIGN_BOTTOM)
        builder.setSpan(sceneIconSpan, start, end, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        return builder
    }
}

package fr.tvbarthel.scene.things.file

import android.content.Context
import java.io.File

class FileManager(private val context: Context) {

    fun createFile(fileName: String): File {
        val privateFileDir = context.filesDir
        val file = File(privateFileDir, fileName)
        if (!file.exists()) file.createNewFile()

        return file
    }
}
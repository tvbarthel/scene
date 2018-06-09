package fr.tvbarthel.scene.storage

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.util.Log
import java.io.*

/**
 * Used to encapsulate every logic linked to the persistence.
 */
class StorageManager(var context: Context) {

    private val sharedPreferences: SharedPreferences

    private var scene: String? = null

    companion object {
        @JvmStatic
        var STORAGE: String = "storage"

        @JvmStatic
        var SCENE_KEY: String = "storage_scene"

        @JvmStatic
        var TEMP_FILE: String = "tmp.jpg"

        @JvmStatic
        var TAG = StorageManager::class.java.simpleName

    }

    init {
        sharedPreferences = context.getSharedPreferences(STORAGE, Context.MODE_PRIVATE)
    }

    /**
     * Used to remember the last used scene.
     *
     * @param scene scene to remember.
     */
    fun storeScene(scene: String) {
        this.scene = scene
        sharedPreferences.edit().putString(SCENE_KEY, scene).apply();
    }

    /**
     * Used to load the last used scene.
     *
     * @return last stored scene.
     */
    fun loadScene(): String? = scene ?: sharedPreferences.getString(SCENE_KEY, scene)


    /**
     * Used to store an image locally.
     *
     * Will create a copy.
     *
     * @return path of the local temp file holding the image.
     */
    fun storeImage(uri: Uri): String? {
        var inputStream: InputStream? = null
        var filePath: String? = null

        uri.authority?.let {
            try {
                inputStream = context.getContentResolver().openInputStream(uri)
                val photoFile = createTemporalFileFrom(inputStream)
                filePath = photoFile!!.path
            } catch (e: FileNotFoundException) {
                Log.e(TAG, "failed to store the image: FileNotFoundException" + e.message)
            } catch (e: IOException) {
                Log.e(TAG, "failed to store the image: IOException" + e.message)
            } finally {
                try {
                    inputStream!!.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
        }

        return filePath
    }

    @Throws(IOException::class)
    private fun createTemporalFileFrom(inputStream: InputStream?): File? {
        var targetFile: File? = null

        if (inputStream != null) {
            targetFile = createTemporalFile()
            val outputStream = FileOutputStream(targetFile)
            inputStream.copyTo(outputStream)
            outputStream.flush()
            outputStream.close()

        }

        return targetFile
    }

    private fun createTemporalFile(): File {
        return File(context.externalCacheDir, TEMP_FILE) // context needed
    }
}
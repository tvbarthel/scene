package fr.tvbarthel.scene.storage

import android.content.Context
import android.content.SharedPreferences

/**
 * Used to encapsulate every logic linked to the persistence.
 */
public class StorageManager(context: Context) {

    private val sharedPreferences: SharedPreferences

    private var scene: String? = null

    companion object {
        @JvmStatic
        var STORAGE: String = "storage"
        var SCENE_KEY: String = "storage_scene"
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
}
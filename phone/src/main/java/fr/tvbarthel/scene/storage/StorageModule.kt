package fr.tvbarthel.scene.storage

import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * Used to provide every component linked to the persistence layer.
 */
@Module
class StorageModule {

    @Provides
    fun provideStorageManager(context: Context): StorageManager = StorageManager(context)

}
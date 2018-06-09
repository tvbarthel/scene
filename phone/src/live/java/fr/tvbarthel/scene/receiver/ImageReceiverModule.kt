package fr.tvbarthel.scene.receiver

import dagger.Module
import dagger.Provides
import fr.tvbarthel.scene.storage.StorageManager

/**
 * Module used to provide every component required for the photo upload.
 */
@Module
class ImageReceiverModule {

    @Provides
    fun provideImageReceiverPresenter(storageManager: StorageManager): ImageReceiverContract.Presenter {
        return ImageReceiverPresenterImpl(storageManager)
    }
}

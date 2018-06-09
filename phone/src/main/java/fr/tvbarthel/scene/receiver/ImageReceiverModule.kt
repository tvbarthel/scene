package fr.tvbarthel.scene.receiver

import dagger.Module
import dagger.Provides

/**
 * Module used to provide every component required for the photo upload.
 */
@Module
class ImageReceiverModule {

    @Provides
    fun provideImageReceiverPresenter(): ImageReceiverContract.Presenter = ImageReceiverPresenterImpl()
}
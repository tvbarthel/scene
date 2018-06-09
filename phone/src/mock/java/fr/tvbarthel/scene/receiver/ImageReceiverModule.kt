package fr.tvbarthel.scene.receiver

import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * Module used to provide every component required for the photo upload.
 */
@Module
class ImageReceiverModule {

    @Provides
    fun provideImageReceiverPresenter(context: Context): ImageReceiverContract.Presenter = ImageReceiverPresenterImpl(context)
}

package fr.tvbarthel.scene

import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * provide every components linked to the application.
 */
@Module
class ApplicationModule(val context: Context) {

    @Provides
    fun provideContext(): Context = context

}
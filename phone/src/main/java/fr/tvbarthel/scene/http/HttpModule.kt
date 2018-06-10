package fr.tvbarthel.scene.http

import dagger.Module
import dagger.Provides

/**
 * Used to provide every component required for the http client.
 */
@Module
class HttpModule {

    @Provides
    fun provideSceneClient(): SceneClient = SceneClient()
}
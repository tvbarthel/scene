package fr.tvbarthel.scene

import dagger.Component

@Component(modules = [(ApplicationModule::class)])
interface SceneComponent {
    fun inject(imageReceiverActivity: ImageReceiverActivity)
}
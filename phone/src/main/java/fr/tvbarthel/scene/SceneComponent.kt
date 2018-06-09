package fr.tvbarthel.scene

import dagger.Component
import fr.tvbarthel.scene.receiver.ImageReceiverActivity
import fr.tvbarthel.scene.receiver.ImageReceiverModule

@Component(modules = [
    (ApplicationModule::class),
    (ImageReceiverModule::class)
])
interface SceneComponent {
    fun inject(imageReceiverActivity: ImageReceiverActivity)
}
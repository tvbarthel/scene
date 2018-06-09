package fr.tvbarthel.scene

import dagger.Component
import fr.tvbarthel.scene.receiver.ImageReceiverActivity
import fr.tvbarthel.scene.receiver.ImageReceiverModule
import fr.tvbarthel.scene.storage.StorageModule

@Component(modules = [
    (ApplicationModule::class),
    (ImageReceiverModule::class),
    (StorageModule::class)
])
interface SceneComponent {
    fun inject(imageReceiverActivity: ImageReceiverActivity)
}
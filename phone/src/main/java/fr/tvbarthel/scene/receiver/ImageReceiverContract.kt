package fr.tvbarthel.scene.receiver

import android.content.Intent
import android.graphics.Bitmap

/**
 * Interface used to hold contracts of both view and presenter responsible for
 * handling a photo selected by the user and send it to the user's Scene.
 */
interface ImageReceiverContract {

    /**
     * Define the contract of any presenter able to send a photo to the Scene.
     */
    interface Presenter {

        /**
         * Must hold the view to which the presenter is attached.
         */
        fun attach(view: View)

        /**
         * Must un hold the view to which the presenter was attached and stop any background process
         * started.
         */
        fun detach(view: View)

        /**
         * Must extract and prepare the image to be send.
         *
         * @param intent intent of the image shared by the user.
         */
        fun prepareImage(intent: Intent)

        /**
         * Must send the prepared image if any.
         *
         * @param scene scene to which the prepared image must be send.
         */
        fun sendImage(scene: String)

        /**
         * Must return the last Scene used or null if none was already used.
         */
        fun lastScene(): String?
    }

    /**
     * Define the contract of any view able to display the sending of a photo to the Scene.
     */
    interface View {

        /**
         * Must show a visual feedback during the upload.
         */
        fun showLoading()

        /**
         * Must show the error to the user and allow him to restart.
         */
        fun showError(error: String)

        /**
         * Must show the success of upload and allow the user to leave the screen.
         */
        fun showSuccess()

        /**
         * Must display the preview to the user.
         *
         * @param path preview file path
         */
        fun showPreview(preview: Bitmap?)

        /**
         * Must display a send option to the user.
         */
        fun showSend()

    }
}
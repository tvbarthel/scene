package fr.tvbarthel.scene.things.server

import android.util.Log
import fi.iki.elonen.NanoHTTPD
import fr.tvbarthel.scene.things.file.FileManager
import okio.Okio
import java.io.File


class PhotoUploadServerManager(private val fileManager: FileManager,
                               port: Int,
                               private val listener: (photo: File) -> Unit)
    : NanoHTTPD(port) {

    companion object {
        private const val TAG = "PhotoUploadServerManager"
        private const val IMAGE_FILE_NAME = "image-background"
        private const val UPLOAD_PHOTO_PATH = "/photo/upload"
    }

    override fun serve(session: IHTTPSession?): Response {
        if (session !is HTTPSession) {
            return internalErrorResponse()
        }

        if (session.method != Method.POST) {
            return internalErrorResponse()
        }

        if (UPLOAD_PHOTO_PATH != session.uri) {
            return internalErrorResponse()
        }

        val uploadedPhoto = getUploadedPhoto(session) ?: return internalErrorResponse()
        listener(uploadedPhoto)

        return newFixedLengthResponse(Response.Status.OK, NanoHTTPD.MIME_PLAINTEXT, "OK")
    }

    private fun internalErrorResponse() =
            newFixedLengthResponse(Response.Status.INTERNAL_ERROR, MIME_PLAINTEXT, "Error")

    private fun getUploadedPhoto(session: HTTPSession): File? {
        try {
            val bodySize = session.bodySize
            val inputStream = session.inputStream
            val source = Okio.source(inputStream)
            val file = fileManager.createFile(IMAGE_FILE_NAME)
            val buffer = Okio.buffer(Okio.sink(file))

            var totalBytesRead: Long = 0
            var readCount: Long
            while (totalBytesRead < bodySize) {
                readCount = source.read(buffer.buffer(), 8192)
                if (readCount < 0) break
                totalBytesRead += readCount
            }

            buffer.flush()

            return file
        } catch (e: Exception) {
            Log.e(TAG, "error while getting the photo.", e)
            return null
        }
    }

}
package io.cutebot.crashmap.service

import io.cutebot.crashmap.service.manage.AccidentManageService
import io.cutebot.crashmap.service.model.ExistedAccident
import io.cutebot.telegram.bot.model.Document
import io.cutebot.telegram.bot.model.Photo
import io.cutebot.telegram.bot.model.User
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.http.MediaType.IMAGE_JPEG
import org.springframework.stereotype.Service
import java.nio.file.Files
import java.nio.file.Path
import java.util.UUID
import javax.annotation.PostConstruct

@Service
class AccidentService(
        private val accidentManageService: AccidentManageService,
        private val submitService: SubmitService,
        @Value("\${media.dir}")
        private val mediaDir: String
) {

    @PostConstruct
    fun postConstruct() {
        Files.createDirectories(Path.of(mediaDir))
    }

    fun getOrCreateCurrentAccident(user: User): ExistedAccident {
        return accidentManageService.getOrCreateCurrentAccident(user)
    }

    fun addAccidentText(accidentId: Int, message: String) {
        accidentManageService.addAccidentText(accidentId, message)
    }

    fun addAccidentPhoto(accidentId: Int, photo: Photo, message: String) {
        val fileName = UUID.randomUUID().toString() + ".jpg"
        val path = Path.of("$mediaDir/$fileName")
        Files.copy(photo.large().getInputStream(), path);
        val fullPath = path.toAbsolutePath().toString()
        accidentManageService.addAccidentMedia(accidentId, message, fullPath, IMAGE_JPEG.toString())
    }

    fun submit(accidentId: Int) {
        val accident = accidentManageService.getAccident(accidentId)
        submitService.submitAccident(accident)
        accidentManageService.submit(accidentId)
    }

    fun addAccidentDoc(currentAccidentId: Int, document: Document) {
        TODO("Not yet implemented")
    }

}
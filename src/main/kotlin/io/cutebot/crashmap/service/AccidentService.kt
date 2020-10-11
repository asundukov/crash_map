package io.cutebot.crashmap.service

import io.cutebot.crashmap.service.manage.AccidentManageService
import io.cutebot.crashmap.service.model.ExistedAccident
import io.cutebot.crashmap.service.model.FileType
import io.cutebot.telegram.bot.model.Contact
import io.cutebot.telegram.bot.model.Document
import io.cutebot.telegram.bot.model.Location
import io.cutebot.telegram.bot.model.Photo
import io.cutebot.telegram.bot.model.User
import io.cutebot.telegram.bot.model.Video
import io.cutebot.telegram.bot.model.VideoNote
import io.cutebot.telegram.bot.model.Voice
import org.springframework.beans.factory.annotation.Value
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

    fun addAccidentText(accidentId: Int, message: String, messageId: Long) {
        accidentManageService.addAccidentText(accidentId, message, messageId)
    }

    fun addAccidentPhoto(accidentId: Int, photo: Photo, message: String, messageId: Long) {
        val fileName = UUID.randomUUID().toString() + ".jpg"
        val path = Path.of("$mediaDir/$fileName")
        Files.copy(photo.large().getInputStream(), path);
        val fullPath = path.toAbsolutePath().toString()
        accidentManageService.addAccidentFile(accidentId, message, messageId, fullPath, FileType.PHOTO, photo.large().fileId)
    }

    fun submit(accidentId: Int) {
        val accident = accidentManageService.getAccident(accidentId)
        submitService.submitAccident(accident)
        accidentManageService.submit(accidentId)
    }

    fun addAccidentDoc(accidentId: Int, document: Document, message: String, messageId: Long) {
        accidentManageService.addAccidentFile(accidentId, message, messageId, "", FileType.DOCUMENT, document.fileId)
    }

    fun addAccidentVideo(accidentId: Int, video: Video, message: String, messageId: Long) {
        accidentManageService.addAccidentFile(accidentId, message, messageId, "", FileType.VIDEO, video.fileId)
    }

    fun addAccidentVideoNote(accidentId: Int, video: VideoNote, messageId: Long) {
        accidentManageService.addAccidentFile(accidentId, "", messageId, "", FileType.VIDEO, video.fileId)
    }

    fun addAccidentContact(accidentId: Int, contact: Contact, messageId: Long) {
        accidentManageService.addAccidentContact(
                accidentId = accidentId,
                messageId = messageId,
                firstName = contact.firstName,
                lastName = contact.lastName,
                phone = contact.phoneNumber,
                telegramUserId = contact.userId,
                vcard = contact.vcard
        )
    }

    fun addAccidentVoice(accidentId: Int, voice: Voice, messageId: Long) {
        accidentManageService.addAccidentFile(accidentId, "", messageId, "", FileType.AUDIO, voice.fileId)
    }

    fun addAccidentLocation(accidentId: Int, location: Location, messageId: Long) {
        accidentManageService.addAccidentLocation(
                accidentId = accidentId,
                messageId = messageId,
                longitude = location.longitude,
                latitude = location.latitude,
                address = location.address,
                title = location.title,
                foursquareId = location.foursquareId,
                foursquareType = location.foursquareType
        )
    }

}

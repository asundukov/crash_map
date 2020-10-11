package io.cutebot.crashmap.service.manage

import io.cutebot.crashmap.domain.accident.AccidentMessageRepository
import io.cutebot.crashmap.domain.accident.AccidentRepository
import io.cutebot.crashmap.domain.accident.model.AccidentEntity
import io.cutebot.crashmap.domain.accident.model.AccidentMessageContactEntity
import io.cutebot.crashmap.domain.accident.model.AccidentMessageEntity
import io.cutebot.crashmap.domain.accident.model.AccidentMessageFileEntity
import io.cutebot.crashmap.domain.accident.model.AccidentMessageLocationEntity
import io.cutebot.crashmap.domain.usr.model.UsrEntity
import io.cutebot.crashmap.service.model.ExistedAccident
import io.cutebot.crashmap.service.model.FileType
import io.cutebot.telegram.bot.model.User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Calendar

@Service
class AccidentManageService(
        private val usrManageService: UsrManageService,
        private val accidentRepository: AccidentRepository,
        private val accidentMessageRepository: AccidentMessageRepository
) {

    @Transactional(readOnly = true)
    fun getAccident(accidentId: Int): ExistedAccident {
        return ExistedAccident(accidentRepository.getOne(accidentId))
    }

    @Transactional
    fun getOrCreateCurrentAccident(user: User): ExistedAccident {
        return ExistedAccident(getOrCreateNewAccident(user))
    }

    @Transactional
    fun addAccidentText(accidentId: Int, message: String, messageId: Long) {
        addText(accidentId, message, messageId)
    }

    @Transactional
    fun submit(accidentId: Int) {
        val accident = accidentRepository.getOne(accidentId)
        accident.submitted = true
        accidentRepository.save(accident)
    }

    internal fun addText(accidentId: Int, message: String, messageId: Long): AccidentMessageEntity {
        val accident = accidentRepository.getOne(accidentId)
        return createAccidentMessage(accident, message, messageId)
    }

    private fun createAccidentMessage(
            accident: AccidentEntity,
            message: String,
            messageId: Long
    ): AccidentMessageEntity {
        val accidentMessageEntity = AccidentMessageEntity(
                accidentMessageId = 0,
                accident = accident,
                createdOn = Calendar.getInstance(),
                message = message,
                files = ArrayList(),
                contacts = ArrayList(),
                locations = ArrayList(),
                tgMessageId = messageId
        )
        accidentMessageRepository.save(accidentMessageEntity)
        return accidentMessageEntity
    }

    internal fun getOrCreateNewAccident(user: User): AccidentEntity {
        val usr = usrManageService.getOrCreateById(user)
        return accidentRepository.findByUsrAndSubmittedOrderByAccidentIdDesc(usr, false)
                ?: createNewAccident(usr)
    }

    private fun createNewAccident(usr: UsrEntity): AccidentEntity {
        val accidentEntity = AccidentEntity(
                accidentId = 0,
                submitted = false,
                usr = usr,
                createdOn = Calendar.getInstance(),
                messages = emptyList()
        )
        accidentRepository.save(accidentEntity)
        return accidentEntity;
    }

    fun addAccidentFile(
            accidentId: Int,
            message: String,
            messageId: Long,
            filePath: String,
            fileType: FileType,
            fileId: String
    ) {
        val accident = accidentRepository.getOne(accidentId)
        val accidentMessage = createAccidentMessage(accident, message, messageId)
        val file = AccidentMessageFileEntity(
                accidentMessageFileId = 0,
                createdOn = Calendar.getInstance(),
                filePath = filePath,
                accidentMessage = accidentMessage,
                fileType = fileType,
                tgFileId = fileId
        )
        accidentMessage.files.add(file)
        accidentMessageRepository.save(accidentMessage)
    }

    fun addAccidentContact(
            accidentId: Int,
            messageId: Long,
            firstName: String,
            lastName: String,
            phone: String,
            telegramUserId: Long?,
            vcard: String?
    ) {
        val accident = accidentRepository.getOne(accidentId)
        val accidentMessage = createAccidentMessage(accident, "", messageId)
        val contact = AccidentMessageContactEntity(
                accidentMessageContactId = 0,
                createdOn = Calendar.getInstance(),
                firstName = firstName,
                lastName = lastName,
                phone = phone,
                accidentMessage = accidentMessage,
                telegramUserId = telegramUserId,
                vcard = vcard
        )
        accidentMessage.contacts.add(contact)
        accidentMessageRepository.save(accidentMessage)
    }

    fun addAccidentLocation(
            accidentId: Int,
            messageId: Long,
            longitude: Double,
            latitude: Double,
            address: String,
            title: String,
            foursquareId: String?,
            foursquareType: String?
    ) {
        val accident = accidentRepository.getOne(accidentId)
        val accidentMessage = createAccidentMessage(accident, "", messageId)
        val contact = AccidentMessageLocationEntity(
                accidentMessageLocationId = 0,
                createdOn = Calendar.getInstance(),
                accidentMessage = accidentMessage,
                longitude = longitude,
                latitude = latitude,
                address = address,
                title = title,
                foursquareId = foursquareId,
                foursquareType = foursquareType
        )
        accidentMessage.locations.add(contact)
        accidentMessageRepository.save(accidentMessage)
    }

}

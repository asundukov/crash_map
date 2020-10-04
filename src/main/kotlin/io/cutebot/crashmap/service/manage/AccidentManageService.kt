package io.cutebot.crashmap.service.manage

import io.cutebot.crashmap.domain.accident.AccidentMessageRepository
import io.cutebot.crashmap.domain.accident.AccidentRepository
import io.cutebot.crashmap.domain.accident.model.AccidentEntity
import io.cutebot.crashmap.domain.accident.model.AccidentMessageEntity
import io.cutebot.crashmap.domain.accident.model.AccidentMessageMediaEntity
import io.cutebot.crashmap.domain.usr.model.UsrEntity
import io.cutebot.crashmap.service.model.ExistedAccident
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
    fun addAccidentText(accidentId: Int, message: String) {
        addText(accidentId, message)
    }

    @Transactional
    fun submit(accidentId: Int) {
        val accident = accidentRepository.getOne(accidentId)
        accident.submitted = true
        accidentRepository.save(accident)
    }

    internal fun addText(accidentId: Int, message: String): AccidentMessageEntity {
        val accident = accidentRepository.getOne(accidentId)
        return createAccidentMessage(accident, message)
    }

    private fun createAccidentMessage(
            accident: AccidentEntity,
            message: String
    ): AccidentMessageEntity {
        val accidentMessageEntity = AccidentMessageEntity(
                accidentMessageId = 0,
                accident = accident,
                createdOn = Calendar.getInstance(),
                message = message,
                media = ArrayList()
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

    fun addAccidentMedia(accidentId: Int, message: String, fileName: String, mediaType: String) {
        val accident = accidentRepository.getOne(accidentId)
        val accidentMessage = createAccidentMessage(accident, message)
        val media = AccidentMessageMediaEntity(
                accidentMessageMediaId = 0,
                createdOn = Calendar.getInstance(),
                filePath = fileName,
                accidentMessage = accidentMessage,
                mediaType = mediaType
        )
        accidentMessage.media.add(media)
        accidentMessageRepository.save(accidentMessage)
    }

}

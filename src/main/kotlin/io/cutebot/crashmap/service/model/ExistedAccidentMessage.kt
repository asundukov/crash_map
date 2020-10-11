package io.cutebot.crashmap.service.model

import io.cutebot.crashmap.domain.accident.model.AccidentMessageEntity
import kotlin.streams.toList

class ExistedAccidentMessage(accidentMessage: AccidentMessageEntity) {
    val id = accidentMessage.accidentMessageId
    val tgMessageId = accidentMessage.tgMessageId
    val message = accidentMessage.message
    val accidentId = accidentMessage.accident.accidentId
    val files = accidentMessage.files
            .stream().map { ExistedAccidentMessageFile(it) }.toList()
    val contacts = accidentMessage.contacts
            .stream().map { ExistedAccidentMessageContact(it) }.toList()

    val locations = accidentMessage.locations
            .stream().map { ExistedAccidentMessageLocation(it) }.toList()

    fun noAttaches(): Boolean {
        return files.isEmpty() && contacts.isEmpty() && locations.isEmpty()
    }
}

package io.cutebot.crashmap.service.model

import io.cutebot.crashmap.domain.accident.model.AccidentMessageEntity
import kotlin.streams.toList

class ExistedAccidentMessage(accidentMessage: AccidentMessageEntity) {
    val id = accidentMessage.accidentMessageId
    val message = accidentMessage.message
    val accidentId = accidentMessage.accident.accidentId
    val media = accidentMessage.media
            .stream().map { ExistedAccidentMessageMedia(it) }.toList()
}

package io.cutebot.crashmap.service.model

import io.cutebot.crashmap.domain.accident.model.AccidentMessageMediaEntity

class ExistedAccidentMessageMedia(media: AccidentMessageMediaEntity) {
    val id = media.accidentMessageMediaId
    val accidentMessageId = media.accidentMessage.accidentMessageId
    val filePath = media.filePath
    val mediaType = media.mediaType
}

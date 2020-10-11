package io.cutebot.crashmap.service.model

import io.cutebot.crashmap.domain.accident.model.AccidentMessageFileEntity

class ExistedAccidentMessageFile(file: AccidentMessageFileEntity) {
    val id = file.accidentMessageFileId
    val accidentMessageId = file.accidentMessage.accidentMessageId
    val filePath = file.filePath
    val mediaType = file.fileType
}

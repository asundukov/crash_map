package io.cutebot.crashmap.service.model

import io.cutebot.crashmap.domain.accident.model.AccidentMessageLocationEntity

class ExistedAccidentMessageLocation(location: AccidentMessageLocationEntity) {
    val id = location.accidentMessageLocationId
    val accidentMessageId = location.accidentMessage.accidentMessageId
    val longitude = location.longitude
    val latitude = location.latitude
    val address = location.address
    val title = location.title
}

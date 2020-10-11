package io.cutebot.crashmap.service.model

import io.cutebot.crashmap.domain.accident.model.AccidentEntity
import kotlin.streams.toList

class ExistedAccident(accidentEntity: AccidentEntity) {
    val id = accidentEntity.accidentId
    val submitted = accidentEntity.submitted
    val createdOn = accidentEntity.createdOn

    val user = ExistedUser(accidentEntity.usr)
    val messages = accidentEntity.messages
            .stream().map { ExistedAccidentMessage(it) }
            .toList()

}

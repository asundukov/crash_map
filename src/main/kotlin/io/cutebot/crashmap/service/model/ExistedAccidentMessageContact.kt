package io.cutebot.crashmap.service.model

import io.cutebot.crashmap.domain.accident.model.AccidentMessageContactEntity

class ExistedAccidentMessageContact(contact: AccidentMessageContactEntity) {
    val id = contact.accidentMessageContactId
    val accidentMessageId = contact.accidentMessage.accidentMessageId
    val firstName = contact.firstName
    val lastName = contact.lastName
    val phone = contact.phone

}

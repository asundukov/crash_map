package io.cutebot.crashmap.domain.usr.model

class ExistedUsr(entity: UsrEntity) {
    val id = entity.usrId
    val languageCode = entity.languageCode
}

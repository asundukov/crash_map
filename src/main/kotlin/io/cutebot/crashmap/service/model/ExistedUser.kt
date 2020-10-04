package io.cutebot.crashmap.service.model

import io.cutebot.crashmap.domain.usr.model.UsrEntity

class ExistedUser(usr: UsrEntity) {
    val id = usr.usrId
    val firstName = usr.firstName
    val lastName = usr.lastName
    val userName = usr.userName

    fun compileTgName(): String {
        if (userName.isEmpty()) {
            return "Anonym (id: $id name: $lastName $firstName)"
        } else {
            return "@$userName"
        }
    }

}

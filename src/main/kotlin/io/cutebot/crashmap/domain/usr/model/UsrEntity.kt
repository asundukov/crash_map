package io.cutebot.crashmap.domain.usr.model


import java.util.Calendar
import javax.persistence.Entity
import javax.persistence.Id


@Entity(name = "usr")
data class UsrEntity (
    @Id
    val usrId: Long,

    val firstName: String,

    val lastName: String,

    val userName: String,

    val languageCode: String,

    val createdOn: Calendar

)

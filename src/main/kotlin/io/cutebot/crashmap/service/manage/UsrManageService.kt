package io.cutebot.crashmap.service.manage

import io.cutebot.crashmap.domain.usr.UsrRepository
import io.cutebot.crashmap.domain.usr.model.ExistedUsr
import io.cutebot.crashmap.domain.usr.model.UsrEntity
import io.cutebot.telegram.bot.model.User
import io.cutebot.telegram.client.model.TgUser
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.Calendar

@Service
class UsrManageService(
        private val usrRepository: UsrRepository
) {

    internal fun getOrCreateById(user: User): UsrEntity {
        val existed = usrRepository.findByIdOrNull(user.id)
        existed?.let { return it }

        val newEntity = UsrEntity(
                usrId = user.id,
                userName = user.userName ?: "",
                firstName = user.firstName,
                lastName = user.lastName ?: "",
                createdOn = Calendar.getInstance(),
                languageCode = user.languageCode ?: ""
        )
        usrRepository.save(newEntity)
        return newEntity
    }

}
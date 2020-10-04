package io.cutebot.crashmap.service

import io.cutebot.crashmap.service.model.ExistedAccident
import io.cutebot.telegram.client.TelegramApi
import io.cutebot.telegram.client.model.TgSendPhoto
import io.cutebot.telegram.client.model.TgSendTextMessage
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.File

@Service
class SubmitService(
        @Value("\${bot.moderators_chat_id}")
        private val chatId: Long,
        @Value("\${bot.token}")
        private val botToken: String
) {
    private val telegramApi: TelegramApi = TelegramApi()

    fun submitAccident(accident: ExistedAccident) {
        var counter = 1
        var message = ""
        for (item in accident.messages) {
            if (item.media.isNotEmpty()) {
                if (message.isNotEmpty()) {
                    message = "Сообщение ($counter) от " + accident.user.compileTgName() + "\n\n$message"
                    val sendTextMessage = TgSendTextMessage(chatId, message)
                    telegramApi.sendMessage(botToken, sendTextMessage)
                    counter++
                    message = ""
                }
                counter++
                for (photoMessage in item.media) {
                    val sendPhotoMessage = TgSendPhoto(
                            chatId,
                            File(photoMessage.filePath),
                            item.message
                    )
                    telegramApi.sendPhoto(botToken, sendPhotoMessage)
                }
            } else {
                message += item.message + "\n\n"
            }
        }
        if (message.isNotEmpty()) {
            message = "Сообщение ($counter) от " + accident.user.compileTgName() + "\n\n$message"
            val sendTextMessage = TgSendTextMessage(chatId, message)
            telegramApi.sendMessage(botToken, sendTextMessage)
        }
    }
}

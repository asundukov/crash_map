package io.cutebot.crashmap.service

import io.cutebot.crashmap.service.model.ExistedAccident
import io.cutebot.crashmap.service.model.ExistedAccidentMessage
import io.cutebot.crashmap.tools.StringCarefulDivider
import io.cutebot.telegram.client.TelegramApi
import io.cutebot.telegram.client.model.TgForwardMessage
import io.cutebot.telegram.client.model.TgSendTextMessage
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

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
        for (accidentMessage in accident.messages) {
            if (accidentMessage.noAttaches()) {
                message += accidentMessage.message + "\n"
            } else {
                if (message.isNotEmpty()) {
                    message = "Сообщение ($counter) от " + accident.user.compileTgName() + "\n\n$message"
                    sendMessageByChunks(message)
                    counter++
                    message = ""
                }
                counter++
                for (fileMessage in accidentMessage.files) {
                    forwardMessage(accident, accidentMessage)
                }
                for (contactMessage in accidentMessage.contacts) {
                    forwardMessage(accident, accidentMessage)
                }
                for (locationMessage in accidentMessage.locations) {
                    forwardMessage(accident, accidentMessage)
                }
            }
        }

        if (message.isNotEmpty()) {
            message = "Сообщение ($counter) от " + accident.user.compileTgName() + "\n\n$message"
            sendMessageByChunks(message)
        }
    }

    private fun forwardMessage(accident: ExistedAccident, accidentMessage: ExistedAccidentMessage) {
        val forwardMessage = TgForwardMessage(
                chatId = chatId,
                fromChatId = accident.user.id,
                messageId = accidentMessage.tgMessageId
        )
        telegramApi.forwardMessage(botToken, forwardMessage)
    }

    private fun sendMessageByChunks(message: String) {
        val messages = stringCarefulDivider.divide(message)
        for (messageItem in messages) {
            val sendTextMessage = TgSendTextMessage(chatId, messageItem)
            telegramApi.sendMessage(botToken, sendTextMessage)
        }
    }

    companion object {
        private val stringCarefulDivider = StringCarefulDivider()
    }
}

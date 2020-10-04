package io.cutebot.crashmap.bot.blocks

import io.cutebot.crashmap.service.AccidentService
import io.cutebot.telegram.bot.block.BotBlock
import io.cutebot.telegram.bot.model.DocumentMessage
import io.cutebot.telegram.bot.model.PhotoMessage
import io.cutebot.telegram.bot.model.TextMessage
import io.cutebot.telegram.bot.model.User
import io.cutebot.telegram.client.model.keyboard.TgKeyboard
import io.cutebot.telegram.client.model.keyboard.TgKeyboardRemove
import io.cutebot.telegram.client.model.keyboard.builder.ReplyKeyboardSimpleBuilder
import io.cutebot.telegram.interaction.model.ChatAnswer


class MainBlock(
        private val accidentService: AccidentService
): BotBlock {
    private var nextMessage = welcomeMessage
    private var nextKeyboard: TgKeyboard = TgKeyboardRemove()
    private var currentAccidentId: Int? = null

    override fun getAnswer(): ChatAnswer {
        return ChatAnswer.text(nextMessage, nextKeyboard)
    }

    override fun handleDocument(message: DocumentMessage): BotBlock {
        val currentAccidentId = getCurrentAccidentId(message.user!!)
        accidentService.addAccidentDoc(currentAccidentId, message.document)
        nextMessage = receivedMedia
        nextKeyboard = ReplyKeyboardSimpleBuilder().addRow(finishButton).build()
        return this
    }

    override fun handlePhoto(message: PhotoMessage): BotBlock {
        val currentAccidentId = getCurrentAccidentId(message.user!!)
        accidentService.addAccidentPhoto(currentAccidentId, message.photo, message.message)
        nextMessage = receivedMedia
        nextKeyboard = ReplyKeyboardSimpleBuilder().addRow(finishButton).build()
        return this
    }

    override fun handleText(message: TextMessage): BotBlock {
        val currentAccidentId = getCurrentAccidentId(message.user!!)
        if (message.text == finishButton) {
            accidentService.submit(currentAccidentId)
            return FinishBlock()
        }
        accidentService.addAccidentText(currentAccidentId, message.text)
        nextMessage = receivedMessage
        nextKeyboard = ReplyKeyboardSimpleBuilder().addRow(finishButton).build()
        return this
    }

    private fun getCurrentAccidentId(user: User): Int {
        if (currentAccidentId == null) {
            currentAccidentId = accidentService.getOrCreateCurrentAccident(user).id
        }
        return currentAccidentId!!
    }

    companion object {
        private const val finishButton = "У меня всё."
        private const val welcomeMessage =
"""
<b>Добро пожаловать</b>

Данный бот позволит вам сообщить информацию о ДТП.

Пожалуйста, пришлите информацию о произошедшем ДТП - адрес, время, описание и фотографии/видео.

Это можно делать в несколько сообщений. Всю полученную информацию я отправлю нашим модераторам.

Спасибо, что помогаете улучшать дорожную обстановку через освещение происшествий.
"""

        private const val receivedMessage =
"""
Записал, что-то еще?
"""

        private const val receivedMedia =
"""
Сохранил, что-то еще?
"""
    }
}

package io.cutebot.crashmap.bot.blocks

import io.cutebot.telegram.bot.block.BotBlock
import io.cutebot.telegram.bot.block.BotTextBlock
import io.cutebot.telegram.bot.model.message.TextMessage
import io.cutebot.telegram.interaction.model.ChatAnswer

class FinishBlock : BotTextBlock {
    private var nextMessage = thanksText
    override fun getAnswer(): ChatAnswer {
        return ChatAnswer.text(nextMessage)
    }

    override fun handleText(message: TextMessage): BotBlock {
        nextMessage = pleaseRenew
        return this
    }

    companion object {
        private const val thanksText = "Спасибо! Передам вашу информацию модератору."
        private const val pleaseRenew = "Если вы хотите сообщить о новом дтп, пожалуйста, нажмите /start"
    }

}

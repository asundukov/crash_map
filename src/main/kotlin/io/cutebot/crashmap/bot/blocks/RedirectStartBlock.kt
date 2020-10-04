package io.cutebot.crashmap.bot.blocks

import io.cutebot.crashmap.service.AccidentService
import io.cutebot.telegram.bot.block.BotBlock
import io.cutebot.telegram.bot.block.BotTextBlock
import io.cutebot.telegram.bot.model.TextMessage
import io.cutebot.telegram.interaction.model.ChatAnswer
import org.springframework.stereotype.Service

@Service
class RedirectStartBlock(
        private val accidentService: AccidentService
) : BotTextBlock {
    override fun getAnswer(): ChatAnswer {
        return ChatAnswer.noAnswer()
    }

    override fun handleText(message: TextMessage): BotBlock {
        return MainBlock(accidentService)
    }
}

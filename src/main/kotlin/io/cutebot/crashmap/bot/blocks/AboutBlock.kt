package io.cutebot.crashmap.bot.blocks

import io.cutebot.telegram.bot.block.BotBlock
import io.cutebot.telegram.bot.block.BotTextBlock
import io.cutebot.telegram.bot.model.TextMessage
import io.cutebot.telegram.interaction.model.ChatAnswer
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class AboutBlock(
        @Value("\${bot.contact}")
        private val tgContact: String,
        @Value("\${bot.twitter}")
        private val twitterContact: String
): BotTextBlock {
    val message =
"""
<b>О проекте</b>

Github: <a href="https://github.com/asundukov/crash_map">Crash map</a>

<b>Контакты автора:</b>
Contact: @$tgContact
Channel: @developers_mind
Twitter: <a href="https://twitter.com/$twitterContact">@$twitterContact</a>
"""

    override fun getAnswer(): ChatAnswer {
        return ChatAnswer.text(message)
    }

    override fun handleText(message: TextMessage): BotBlock {
        return FinishBlock()
    }
}

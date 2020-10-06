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
Карта ДТП
https://dtp-stat.ru/
Канал с анализом ДТП @crash_map
Чат проекта @crash_map_chat

Github (бот): https://github.com/asundukov/crash_map

<b>Контакты автора бота:</b>
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

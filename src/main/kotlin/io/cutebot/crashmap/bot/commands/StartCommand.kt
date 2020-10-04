package io.cutebot.crashmap.bot.commands

import io.cutebot.crashmap.bot.blocks.MainBlock
import io.cutebot.crashmap.service.AccidentService
import io.cutebot.telegram.bot.block.BotBlock
import io.cutebot.telegram.bot.block.DoNothingBotBlock.Companion.DO_NOTHING_BOT_BLOCK
import io.cutebot.telegram.bot.command.Command
import io.cutebot.telegram.bot.model.RawMessage
import org.springframework.stereotype.Service

@Service
class StartCommand(
        private val accidentService: AccidentService
) : Command {

    override fun handleCommand(query: String, message: RawMessage): BotBlock {
        if (message.from == null || message.chat.id != message.from!!.id) {
            return DO_NOTHING_BOT_BLOCK
        }
        return MainBlock(accidentService)
    }

    override fun getCommand(): String {
        return "/start"
    }

    override fun getCommandDescription(): String {
        return "show start screen"
    }

    override fun isSystemCommand(): Boolean {
        return true
    }
}

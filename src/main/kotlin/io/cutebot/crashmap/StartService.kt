package io.cutebot.crashmap

import io.cutebot.crashmap.bot.blocks.RedirectStartBlock
import io.cutebot.crashmap.bot.commands.AboutCommand
import io.cutebot.crashmap.bot.commands.StartCommand
import io.cutebot.telegram.BotRunner
import io.cutebot.telegram.bot.DefaultCommandsStatefulBot
import io.cutebot.telegram.bot.command.Command
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class StartService(
        @Value("\${bot.token}")
        private val token: String,
        private val redirectStartBlock: RedirectStartBlock,

        startCommand: StartCommand,
        aboutCommand: AboutCommand
) {
    private val botRunner = BotRunner()

    private val commands: List<Command> = listOf(
            startCommand,
            aboutCommand
    )

    @PostConstruct
    fun init() {
        val bot = DefaultCommandsStatefulBot(
                token = token,
                currentBlock = redirectStartBlock,
                commands = commands
        )
        botRunner.run(bot)
    }
}

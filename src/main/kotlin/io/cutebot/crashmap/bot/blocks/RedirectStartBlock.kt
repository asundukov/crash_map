package io.cutebot.crashmap.bot.blocks

import io.cutebot.crashmap.service.AccidentService
import io.cutebot.telegram.bot.block.BotBlock
import io.cutebot.telegram.bot.block.BotTextBlock
import io.cutebot.telegram.bot.model.message.AudioMessage
import io.cutebot.telegram.bot.model.message.ContactMessage
import io.cutebot.telegram.bot.model.message.DocumentMessage
import io.cutebot.telegram.bot.model.message.LocationMessage
import io.cutebot.telegram.bot.model.message.PhotoMessage
import io.cutebot.telegram.bot.model.message.TextMessage
import io.cutebot.telegram.bot.model.message.VideoMessage
import io.cutebot.telegram.bot.model.message.VideoNoteMessage
import io.cutebot.telegram.bot.model.message.VoiceMessage
import io.cutebot.telegram.interaction.model.ChatAnswer
import org.springframework.stereotype.Service

@Service
class RedirectStartBlock(
        private val accidentService: AccidentService
) : BotBlock {
    override fun getAnswer(): ChatAnswer {
        return ChatAnswer.noAnswer()
    }

    override fun handleAudio(message: AudioMessage): BotBlock {
        return MainBlock(accidentService)
    }

    override fun handleContact(message: ContactMessage): BotBlock {
        return MainBlock(accidentService)
    }

    override fun handleDocument(message: DocumentMessage): BotBlock {
        return MainBlock(accidentService)
    }

    override fun handleLocation(message: LocationMessage): BotBlock {
        return MainBlock(accidentService)
    }

    override fun handlePhoto(message: PhotoMessage): BotBlock {
        return MainBlock(accidentService)
    }

    override fun handleText(message: TextMessage): BotBlock {
        return MainBlock(accidentService)
    }

    override fun handleVideo(message: VideoMessage): BotBlock {
        return MainBlock(accidentService)
    }

    override fun handleVideoNote(message: VideoNoteMessage): BotBlock {
        return MainBlock(accidentService)
    }

    override fun handleVoice(message: VoiceMessage): BotBlock {
        return MainBlock(accidentService)
    }


}

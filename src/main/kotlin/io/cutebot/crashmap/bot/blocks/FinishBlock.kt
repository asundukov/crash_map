package io.cutebot.crashmap.bot.blocks

import io.cutebot.telegram.bot.block.BotBlock
import io.cutebot.telegram.bot.model.message.ContactMessage
import io.cutebot.telegram.bot.model.message.DocumentMessage
import io.cutebot.telegram.bot.model.message.LocationMessage
import io.cutebot.telegram.bot.model.message.PhotoMessage
import io.cutebot.telegram.bot.model.message.TextMessage
import io.cutebot.telegram.bot.model.message.VideoMessage
import io.cutebot.telegram.bot.model.message.VideoNoteMessage
import io.cutebot.telegram.bot.model.message.VoiceMessage
import io.cutebot.telegram.interaction.model.ChatAnswer

class FinishBlock : BotBlock {
    private var nextMessage = thanksText
    override fun getAnswer(): ChatAnswer {
        return ChatAnswer.text(nextMessage)
    }

    override fun handleContact(message: ContactMessage): BotBlock {
        nextMessage = pleaseRenew
        return this
    }

    override fun handleDocument(message: DocumentMessage): BotBlock {
        nextMessage = pleaseRenew
        return this
    }

    override fun handleLocation(message: LocationMessage): BotBlock {
        nextMessage = pleaseRenew
        return this
    }

    override fun handlePhoto(message: PhotoMessage): BotBlock {
        nextMessage = pleaseRenew
        return this
    }

    override fun handleText(message: TextMessage): BotBlock {
        nextMessage = pleaseRenew
        return this
    }

    override fun handleVideo(message: VideoMessage): BotBlock {
        nextMessage = pleaseRenew
        return this
    }

    override fun handleVideoNote(message: VideoNoteMessage): BotBlock {
        nextMessage = pleaseRenew
        return this
    }

    override fun handleVoice(message: VoiceMessage): BotBlock {
        nextMessage = pleaseRenew
        return this
    }

    companion object {
        private const val thanksText = "Спасибо! Передам вашу информацию модератору."
        private const val pleaseRenew = "Если вы хотите сообщить о новом дтп, пожалуйста, нажмите /start"
    }

}

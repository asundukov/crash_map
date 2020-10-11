package io.cutebot.crashmap.tools

class StringCarefulDivider(
        private val chunkSize: Int = 4000, //Telegram Message max length (4kb) + service space
        private val dividersByPriority: List<List<String>> = listOf(
                lineDividers,
                sentenceDividers,
                blockDividers,
                wordDividers
        )
) {

    fun divide(message: String): List<String> {
        var msg = message.trim()
        val result = ArrayList<String>()
        while (msg.length > chunkSize) {
            val currentBlock = if (chunkSize < msg.length) msg.substring(0, chunkSize) else msg
            var lastIndex = -1
            var iter = dividersByPriority.iterator()
            while (lastIndex == -1 && iter.hasNext()) {
                lastIndex = getMaxLastIndex(currentBlock, iter.next())
            }
            if (lastIndex == -1) {
                lastIndex = currentBlock.length
            }
            result.add(msg.substring(0, lastIndex).trim())
            msg = msg.substring(lastIndex).trim()
        }
        result.add(msg)
        return result
    }

    private fun getMaxLastIndex(target: String, dividers: List<String>): Int {
        var maxIndex = -1
        for (divider in dividers) {
            var currentIndex = target.lastIndexOf(divider)
            if (currentIndex > -1) {
                currentIndex++
            }
            if (currentIndex > maxIndex) {
                maxIndex = currentIndex
            }
        }
        return maxIndex
    }

    companion object {
        private val lineDividers = listOf("\n")
        private val sentenceDividers = listOf(".", "?", "!")
        private val blockDividers = listOf(",", ":", " - ")
        private val wordDividers = listOf(" ", "-")
    }
}
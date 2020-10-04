package io.cutebot.crashmap.service.model

import java.util.Calendar

class NewAccident(
        val id: Int
) {
    var lastActiveTime = Calendar.getInstance()
}

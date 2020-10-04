package io.cutebot.crashmap.domain.accident.model

import java.util.Calendar
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity(name = "accident_message_media")
data class AccidentMessageMediaEntity(
        @Id
        @GeneratedValue(strategy = IDENTITY)
        @Column(updatable = false)
        val accidentMessageMediaId: Int,

        @ManyToOne
        @JoinColumn(name = "accident_message_id", updatable = false)
        val accidentMessage: AccidentMessageEntity,

        @Column(updatable = false)
        val filePath: String,

        @Column(updatable = false)
        val mediaType: String,

        @Column(updatable = false)
        val createdOn: Calendar
)

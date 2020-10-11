package io.cutebot.crashmap.domain.accident.model

import io.cutebot.crashmap.service.model.FileType
import java.util.Calendar
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity(name = "accident_message_file")
data class AccidentMessageFileEntity(
        @Id
        @GeneratedValue(strategy = IDENTITY)
        @Column(updatable = false)
        val accidentMessageFileId: Int,

        @ManyToOne
        @JoinColumn(name = "accident_message_id", updatable = false)
        val accidentMessage: AccidentMessageEntity,

        @Column(updatable = false)
        val filePath: String,

        @Column(updatable = false)
        @Enumerated(EnumType.STRING)
        val fileType: FileType,

        @Column(updatable = false)
        val createdOn: Calendar,

        @Column(updatable = false)
        val tgFileId: String


)

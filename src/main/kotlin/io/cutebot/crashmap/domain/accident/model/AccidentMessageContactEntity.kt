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

@Entity(name = "accident_message_contact")
data class AccidentMessageContactEntity(
        @Id
        @GeneratedValue(strategy = IDENTITY)
        @Column(updatable = false)
        val accidentMessageContactId: Int,

        @ManyToOne
        @JoinColumn(name = "accident_message_id", updatable = false)
        val accidentMessage: AccidentMessageEntity,

        @Column(updatable = false)
        val firstName: String,

        @Column(updatable = false)
        val lastName: String,

        @Column(updatable = false)
        val phone: String,

        @Column(updatable = false)
        val telegramUserId: Long?,

        @Column(updatable = false)
        val vcard: String?,

        @Column(updatable = false)
        val createdOn: Calendar

)

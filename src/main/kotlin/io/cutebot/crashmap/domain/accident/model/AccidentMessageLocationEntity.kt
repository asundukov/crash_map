package io.cutebot.crashmap.domain.accident.model

import java.util.Calendar
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity(name = "accident_message_location")
data class AccidentMessageLocationEntity(
        @Id
        @GeneratedValue(strategy = IDENTITY)
        @Column(updatable = false)
        val accidentMessageLocationId: Int,

        @ManyToOne
        @JoinColumn(name = "accident_message_id", updatable = false)
        val accidentMessage: AccidentMessageEntity,

        @Column(updatable = false)
        val longitude: Double,

        @Column(updatable = false)
        val latitude: Double,

        @Column(updatable = false)
        val address: String,

        @Column(updatable = false)
        val title: String,

        @Column(updatable = false)
        val foursquareId: String?,

        @Column(updatable = false)
        val foursquareType: String?,

        @Column(updatable = false)
        val createdOn: Calendar

)

package io.cutebot.crashmap.domain.accident.model

import java.util.Calendar
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity(name = "accident_message")
data class AccidentMessageEntity(
        @Id
        @GeneratedValue(strategy = IDENTITY)
        @Column(updatable = false)
        val accidentMessageId: Int,

        @ManyToOne
        @JoinColumn(name = "accident_id", updatable = false)
        val accident: AccidentEntity,

        @OneToMany(mappedBy = "accidentMessage", cascade = [CascadeType.ALL])
        val media: MutableList<AccidentMessageMediaEntity>,

        @Column(updatable = false)
        val message: String,

        @Column(updatable = false)
        val createdOn: Calendar
)

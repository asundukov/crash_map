package io.cutebot.crashmap.domain.accident.model

import io.cutebot.crashmap.domain.usr.model.UsrEntity
import java.util.Calendar
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.OrderBy


@Entity(name = "accident")
data class AccidentEntity(
        @Id
        @GeneratedValue(strategy = IDENTITY)
        @Column(updatable = false)
        val accidentId: Int,

        @ManyToOne
        @JoinColumn(name = "usr_id", updatable = false)
        val usr: UsrEntity,

        @OneToMany(mappedBy = "accident")
        @OrderBy("created_on")
        val messages: List<AccidentMessageEntity>,

        @Column(updatable = true, name = "is_submitted")
        var submitted: Boolean,

        @Column(updatable = false)
        val createdOn: Calendar
)

package io.cutebot.crashmap.domain.accident

import io.cutebot.crashmap.domain.accident.model.AccidentEntity
import io.cutebot.crashmap.domain.usr.model.UsrEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AccidentRepository: JpaRepository<AccidentEntity, Int> {
    fun findByUsrAndSubmittedOrderByAccidentIdDesc(usr: UsrEntity, submitted: Boolean): AccidentEntity?
}

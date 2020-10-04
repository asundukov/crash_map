package io.cutebot.crashmap.domain.accident

import io.cutebot.crashmap.domain.accident.model.AccidentMessageEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AccidentMessageRepository: JpaRepository<AccidentMessageEntity, Int>

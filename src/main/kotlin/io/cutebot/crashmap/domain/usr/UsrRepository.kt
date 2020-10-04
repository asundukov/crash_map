package io.cutebot.crashmap.domain.usr

import io.cutebot.crashmap.domain.usr.model.UsrEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UsrRepository: JpaRepository<UsrEntity, Long>
package com.example.notifire.repository

import com.example.notifire.model.Notification
import java.time.LocalDateTime
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface NotificationRepository: JpaRepository<Notification, Long> {

    fun findAllByCreatedDateBetween(startDate: LocalDateTime, endDate: LocalDateTime): List<Notification>
}

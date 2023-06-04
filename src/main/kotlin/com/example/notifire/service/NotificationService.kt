package com.example.notifire.service

import com.example.notifire.model.Notification
import com.example.notifire.repository.NotificationRepository
import java.time.LocalDateTime
import org.springframework.stereotype.Service

@Service
class NotificationService(
    private val notificationRepository: NotificationRepository
) {

    fun save(notification: Notification): Notification {
        return notificationRepository.save(notification)
    }

    fun findTodayNotifications(): List<Notification> {
        val notifications = ArrayList<Notification>()
        notifications.addAll(notificationRepository.findAllByCreatedDateBetween(
            LocalDateTime.now().minusMinutes(60),
            LocalDateTime.now().minusMinutes(30)
        ))
        notifications.addAll(notificationRepository.findAllByCreatedDateBetween(
            LocalDateTime.now().minusHours(9),
            LocalDateTime.now().minusHours(8).minusMinutes(30)
        ))
        notifications.addAll(notificationRepository.findAllByCreatedDateBetween(
            LocalDateTime.now().minusHours(34),
            LocalDateTime.now().minusHours(33).minusMinutes(30)
        ))
        notifications.addAll(notificationRepository.findAllByCreatedDateBetween(
            LocalDateTime.now().minusHours(34),
            LocalDateTime.now().minusHours(33).minusMinutes(30)
        ))
        notifications.addAll(notificationRepository.findAllByCreatedDateBetween(
            LocalDateTime.now().minusDays(4).minusHours(34),
            LocalDateTime.now().minusDays(4).minusHours(33).minusMinutes(30)
        ))
        notifications.addAll(notificationRepository.findAllByCreatedDateBetween(
            LocalDateTime.now().minusWeeks(3).minusDays(4).minusHours(34),
            LocalDateTime.now().minusWeeks(3).minusDays(4).minusHours(33).minusMinutes(30)
        ))
        notifications.addAll(notificationRepository.findAllByCreatedDateBetween(
            LocalDateTime.now().minusMonths(2).minusWeeks(3).minusDays(4).minusHours(34),
            LocalDateTime.now().minusMonths(2).minusWeeks(3).minusDays(4).minusHours(33).minusMinutes(30)
        ))
        return notifications
    }
}

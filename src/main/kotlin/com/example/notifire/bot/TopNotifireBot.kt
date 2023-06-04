package com.example.notifire.bot

import com.example.notifire.model.Notification
import com.example.notifire.service.NotificationService
import jakarta.annotation.PostConstruct
import java.time.LocalDateTime
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault

@Component
class TopNotifireBot(
    @Value("\${telegram.bot.token}")
    private val token: String,
    @Value("\${telegram.bot.username}")
    private val botUsername: String,
    private val botsApi: TelegramBotsApi,
    private val notificationService: NotificationService
) : TelegramLongPollingBot(token) {

    @PostConstruct
    fun init() {
        botsApi.registerBot(this)
        val botCommands = ArrayList<BotCommand>()
        botCommands.add(BotCommand(BotCommands.REMIND.command, BotCommands.REMIND.description))
        this.execute(SetMyCommands(botCommands, BotCommandScopeDefault(), null))
    }

    override fun getBotUsername() = botUsername

    override fun onUpdateReceived(update: Update?) {
        update?.message?.let {
            if (it.text.startsWith("/remind")) {
                notificationService.save(
                    Notification(
                        message = it.text.removePrefix("/remind"),
                        chatId = it.chatId,
                        createdDate = LocalDateTime.now()
                    )
                )
                sendMessage(it.chatId, "notification was saved")
            }
            if (it.text.startsWith("/check")) {
                notificationService.findTodayNotifications()
            }
        }
    }

    @Scheduled(cron = "0 */30 * ? * *")
    private fun sendNotifications() {
        val notifications = notificationService.findTodayNotifications()
        notifications.forEach {
            sendMessage(it.chatId, it.message)
        }
    }

    fun sendMessage(chatId: Long, text: String) {
        val message = SendMessage()
        message.chatId = chatId.toString()
        message.text = text
        execute(message)
    }

}

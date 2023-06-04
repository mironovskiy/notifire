package com.example.notifire.bot

enum class BotCommands(
    val command: String,
    val description: String
) {
    REMIND(
        "/remind",
        "saves the message and sends a notification after 30 minutes, after 9 hours, after 34 hours, after 5 days, after a month, after 3 months"
    )
}

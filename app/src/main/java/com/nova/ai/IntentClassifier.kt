package com.nova.ai

class IntentClassifier {
    fun classify(command: String): String {
        return when {
            command.contains("search", ignoreCase = true) -> "WEB_SEARCH"
            command.contains("open", ignoreCase = true) -> "APP_LAUNCH"
            command.contains("email", ignoreCase = true) -> "EMAIL_READ"
            command.contains("calendar", ignoreCase = true) -> "CALENDAR_ACCESS"
            command.contains("battery", ignoreCase = true) -> "BATTERY_ALERT"
            command.contains("sms", ignoreCase = true) -> "SMS_HANDLER"
            else -> "CHAT_AI"
        }
    }
}

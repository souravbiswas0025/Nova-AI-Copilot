package com.nova.ai

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AppLauncherActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private lateinit var textToSpeech: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        textToSpeech = TextToSpeech(this, this)
        val appName = intent.getStringExtra("app_name") ?: "Gallery"
        launchApp(appName)
    }

    private fun launchApp(appName: String) {
        val packageManager: PackageManager = packageManager
        val intent = packageManager.getLaunchIntentForPackage(getAppPackage(appName))

        if (intent != null) {
            startActivity(intent)
            textToSpeech.speak("Launching $appName.", TextToSpeech.QUEUE_FLUSH, null, null)
        } else {
            textToSpeech.speak("I couldn't find $appName.", TextToSpeech.QUEUE_FLUSH, null, null)
        }
    }

    private fun getAppPackage(appName: String): String {
        return when (appName.lowercase()) {
            "gallery" -> "com.android.gallery"
            "whatsapp" -> "com.whatsapp"
            "youtube" -> "com.google.android.youtube"
            "maps" -> "com.google.android.apps.maps"
            else -> "unknown"
        }
    }

    override fun onInit(status: Int) {
        if (status != TextToSpeech.ERROR) {
            textToSpeech.language = java.util.Locale.US
        }
    }
}

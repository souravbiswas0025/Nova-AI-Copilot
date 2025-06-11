package com.nova.ai

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.speech.tts.TextToSpeech

class BatteryMonitor(private val context: Context) : BroadcastReceiver(), TextToSpeech.OnInitListener {
    private lateinit var textToSpeech: TextToSpeech

    override fun onReceive(context: Context?, intent: Intent?) {
        val level = intent?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: -1

        if (level < 20) {
            textToSpeech.speak("Battery low!", TextToSpeech.QUEUE_FLUSH, null, null)
        }
    }

    override fun onInit(status: Int) {
        textToSpeech = TextToSpeech(context, this)
    }
}

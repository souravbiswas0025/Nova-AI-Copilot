package com.nova.ai

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var speechRecognizer: SpeechRecognizer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)
        speechRecognizer.setRecognitionListener(object : RecognitionListener {
            override fun onResults(results: Bundle) {
                val command = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)?.get(0)
                handleCommand(command)
            }

            override fun onError(error: Int) {
                Toast.makeText(this@MainActivity, "Error in Speech Recognition", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun handleCommand(command: String?) {
        command?.let {
            when {
                it.contains("search", ignoreCase = true) -> startActivity(Intent(this, WebSearchActivity::class.java))
                it.contains("open", ignoreCase = true) -> startActivity(Intent(this, AppLauncherActivity::class.java))
                it.contains("email", ignoreCase = true) -> startActivity(Intent(this, EmailReadActivity::class.java))
                else -> startActivity(Intent(this, ChatGPTActivity::class.java))
            }
        }
    }
}

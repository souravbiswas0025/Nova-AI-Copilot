package com.nova.ai

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.OkHttpClient
import okhttp3.Request

class EmailReadActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private lateinit var textToSpeech: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        textToSpeech = TextToSpeech(this, this)
        fetchEmails()
    }

    private fun fetchEmails() {
        val apiKey = "YOUR_MICROSOFT_GRAPH_API_KEY"
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://graph.microsoft.com/v1.0/me/messages")
            .addHeader("Authorization", "Bearer $apiKey")
            .build()

        val response = client.newCall(request).execute()
        val emailData = response.body()?.string()

        if (emailData != null) {
            textToSpeech.speak("Here are your latest emails.", TextToSpeech.QUEUE_FLUSH, null, null)
            Toast.makeText(this, emailData, Toast.LENGTH_LONG).show()
        } else {
            textToSpeech.speak("No new emails.", TextToSpeech.QUEUE_FLUSH, null, null)
        }
    }

    override fun onInit(status: Int) {
        if (status != TextToSpeech.ERROR) {
            textToSpeech.language = java.util.Locale.US
        }
    }
}

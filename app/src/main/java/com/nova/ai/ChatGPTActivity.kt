package com.nova.ai

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import okhttp3.OkHttpClient
import okhttp3.Request

class ChatGPTActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatgpt)
    }

    fun getChatResponse(query: String): String {
        val apiKey = "YOUR_OPENAI_API_KEY"
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://api.openai.com/v1/completions")
            .addHeader("Authorization", "Bearer $apiKey")
            .post(okhttp3.RequestBody.create(
                okhttp3.MediaType.parse("application/json"), 
                """{"model":"text-davinci-003","prompt":"$query"}"""
            ))
            .build()

        val response = client.newCall(request).execute()
        return response.body()?.string() ?: "Error fetching response"
    }
}

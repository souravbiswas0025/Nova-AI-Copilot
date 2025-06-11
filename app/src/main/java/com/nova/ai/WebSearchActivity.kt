package com.nova.ai

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.retrofit2.Retrofit
import com.squareup.retrofit2.converter.gson.GsonConverterFactory

class WebSearchActivity : AppCompatActivity() {
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_websearch)
        
        webView = findViewById(R.id.web_view)
        val query = intent.getStringExtra("search_query") ?: ""
        searchWeb(query)
    }

    private fun searchWeb(query: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.bing.microsoft.com/v7.0/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(BingSearchService::class.java)
        service.getResults(query).enqueue(object : Callback<SearchResult> {
            override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
                webView.loadUrl(response.body()?.searchUrl ?: "")
            }

            override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                webView.loadUrl("https://www.google.com/search?q=$query")
            }
        })
    }
}

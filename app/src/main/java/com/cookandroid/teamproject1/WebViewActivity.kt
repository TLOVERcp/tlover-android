package com.cookandroid.teamproject1

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.cookandroid.teamproject1.databinding.ActivityWebViewBinding


//문의하기 웹 뷰 연결
class WebViewActivity : AppCompatActivity() {
//    lateinit var binding : ActivityWebViewBinding
    var myWebView = findViewById<WebView>(R.id.webView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        myWebView.apply {
            webViewClient = WebViewClient()
            webChromeClient = WebChromeClient()
            settings.javaScriptEnabled = true
        }

        myWebView.loadUrl("https://m.naver.com")
    }
}
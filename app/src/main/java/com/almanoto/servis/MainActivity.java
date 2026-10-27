package com.almanoto.servis;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings;
import android.webkit.JavascriptInterface;
import android.content.SharedPreferences;

public class MainActivity extends Activity {
    private SharedPreferences kayitlar;

    public class Depo {
        @JavascriptInterface
        public String oku(String anahtar) {
            return kayitlar.getString(anahtar, "[]");
        }

        @JavascriptInterface
        public void yaz(String anahtar, String deger) {
            kayitlar.edit().putString(anahtar, deger).apply();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        kayitlar = getSharedPreferences("AlmanOtoKayitlar", MODE_PRIVATE);

        WebView webView = new WebView(this);
        setContentView(webView);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);

        webView.addJavascriptInterface(new Depo(), "AndroidDepo");
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("file:///android_asset/index.html");
    }
}

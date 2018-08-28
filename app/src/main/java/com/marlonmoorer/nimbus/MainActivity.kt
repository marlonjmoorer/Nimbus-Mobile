package com.marlonmoorer.nimbus

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import com.dropbox.core.android.Auth
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URL
import android.app.PendingIntent
import android.content.Intent
import com.marlonmoorer.nimbus.providers.*
import net.openid.appauth.*


class MainActivity : AppCompatActivity() {

    private val REDIRECT_URI = "http://localhost"; //"com.marlonmoorer.nimbus:/oauth2redirect"
    private val REDIRECT_URI_ROOT = "com.marlonmoorer.nimbus"
    val USER_AGENT_FAKE = "Mozilla/5.0 (Linux; Android 4.1.1; Galaxy Nexus Build/JRO03C) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19"
    private lateinit var provider: BaseProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.provider=BaseProvider.create(ProviderType.GOOGLE)
        button.setOnClickListener {
            val intent=Intent(this,AccountActivity::class.java)
           startActivity(intent)
        }


    }



    private fun doAuthorization() {
        val request=provider.buildAuthRequest()
        val authService = AuthorizationService(this)
        val authIntent = authService.getAuthorizationRequestIntent(request)
        startActivityForResult(authIntent, 55)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode ==55) {
            val resp = AuthorizationResponse.fromIntent(data!!)
            val ex = AuthorizationException.fromIntent(data)
            resp?.authorizationCode?.let { code->
                val authService = AuthorizationService(this)
                val request=provider.buildTokenRequest(code)
                authService.performTokenRequest(request){response, e ->
                    print("")
                }
            }

        }
    }

    override fun onResume() {
        super.onResume()
    }
}

package com.marlonmoorer.nimbus.providers

import android.content.Context
import android.content.res.Resources
import android.media.session.MediaSession
import android.net.Uri
import java.net.URL
import android.provider.Telephony.Carriers.BEARER
import com.google.gson.Gson
import com.marlonmoorer.nimbus.App
import com.marlonmoorer.nimbus.R
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationServiceConfiguration
import net.openid.appauth.ResponseTypeValues
import net.openid.appauth.TokenRequest
import okhttp3.*
import java.io.IOException


class GoogleProvider:BaseProvider(){


    init {
        client_id= App.Resources.getString(R.string.google_client_id)
        token_url="https://accounts.google.com/o/oauth2/token"
        auth_url="https://accounts.google.com/o/oauth2/auth"
        redirect_url="com.marlonmoorer.nimbus:/oauth2redirect"
        initServiceConfig()
    }



    override fun buildAuthRequest(): AuthorizationRequest {
        val authRequestBuilder = AuthorizationRequest.Builder(
                serviceConfiguration,
                client_id,
                ResponseTypeValues.CODE,
                Uri.parse(redirect_url))
                .setScopes("profile")
                .setCodeVerifier(null)
        return authRequestBuilder.build()
    }

    override fun buildTokenRequest(code: String): TokenRequest {
       return  TokenRequest.Builder(serviceConfiguration,client_id)
                .setAuthorizationCode(code)
                .setRedirectUri(Uri.parse("com.marlonmoorer.nimbus:/oauth2redirect"))
                .setGrantType("authorization_code")
                .build()
    }

    override val additionalParams: Map<String, String>
        get() = mapOf("grant_type" to  "authorization_code")

}


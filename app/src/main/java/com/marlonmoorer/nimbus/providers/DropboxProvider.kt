package com.marlonmoorer.nimbus.providers

import android.content.res.Resources
import android.net.Uri
import com.marlonmoorer.nimbus.App
import com.marlonmoorer.nimbus.R

import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.ResponseTypeValues
import net.openid.appauth.TokenRequest

import java.net.URL

class DropboxProvider:BaseProvider(){

    init {
        client_id= App.Resources.getString(R.string.dropbox_client_id)
        auth_url="https://www.dropbox.com/oauth2/authorize"
        token_url="https://api.dropboxapi.com/oauth2/token"
        //redirect_url="https://nimbus.marlonmoorer.com/oauth2redirect"
        redirect_url="com.marlonmoorer.nimbus://oauth2redirect"
        initServiceConfig()
    }
    override fun buildAuthRequest(): AuthorizationRequest {
        val authRequestBuilder = AuthorizationRequest.Builder(
                serviceConfiguration,
                client_id,
                ResponseTypeValues.TOKEN,
                Uri.parse(redirect_url)
                )
                .setCodeVerifier(null)

                //.setState(null)

        return authRequestBuilder.build()
    }

    override fun buildTokenRequest(code: String): TokenRequest {
        return  TokenRequest.Builder(serviceConfiguration,client_id)
                .setAuthorizationCode(code)
                .setRedirectUri(Uri.parse(redirect_url))
                .setGrantType("authorization_code")
                .build()
    }

    override val additionalParams: Map<String, String>
        get() = mapOf("grant_type" to "authorization_code")

}
package com.marlonmoorer.nimbus.providers

import android.net.Uri

import java.net.URL

//class AmazonProvider(clientId:String,redirectUri:String): OAuth2AuthorizationCodeGrant<OAuth2AccessToken>() {
//    init {
//        this.clientId =clientId
//        this.redirectUri=redirectUri
//    }
//    override fun buildAuthorizationUrl(): URL {
//        val url= Uri.parse("https://www.amazon.com/ap/oa")
//                .buildUpon()
//                .appendQueryParameter("client_id", clientId)
//                .appendQueryParameter("redirect_uri", redirectUri)
//                .appendQueryParameter("response_type", RESPONSE_TYPE)
//                .appendQueryParameter("scope","profile")
//                .build()
//                .toString()
//        return URL(url)
//    }
//
//    override fun exchangeTokenUsingCode(code: String?): Observable<OAuth2AccessToken> {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//}
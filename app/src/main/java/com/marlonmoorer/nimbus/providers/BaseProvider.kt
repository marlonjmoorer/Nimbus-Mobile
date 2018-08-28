package com.marlonmoorer.nimbus.providers

import android.net.Uri
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationServiceConfiguration
import net.openid.appauth.TokenRequest
import java.io.Serializable

abstract class BaseProvider {


    protected lateinit var serviceConfiguration:AuthorizationServiceConfiguration

    protected lateinit var client_id:String
    protected lateinit var token_url:String
    protected lateinit var auth_url:String
    protected lateinit var redirect_url:String

    protected fun initServiceConfig(){
        serviceConfiguration= AuthorizationServiceConfiguration(
                Uri.parse(auth_url),
                Uri.parse(token_url)
        )
    }
    abstract  fun buildAuthRequest():AuthorizationRequest
    abstract  fun buildTokenRequest(code:String):TokenRequest

    abstract  val additionalParams:Map<String,String>

    companion object {
        fun create(type: ProviderType):BaseProvider{
            return when(type){
                ProviderType.GOOGLE-> GoogleProvider()
                ProviderType.DROPBOX->DropboxProvider()
                else-> throw Error("Provider type ${type} is not valide")
            }
        }
    }




}
enum class ProviderType:Serializable{
    GOOGLE,
    AMAZON,
    DROPBOX,
    MICROSOFT
}
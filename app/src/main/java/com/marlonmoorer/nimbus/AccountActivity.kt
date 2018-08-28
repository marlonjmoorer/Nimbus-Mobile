package com.marlonmoorer.nimbus

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.marlonmoorer.nimbus.providers.BaseProvider
import com.marlonmoorer.nimbus.providers.DropboxProvider
import com.marlonmoorer.nimbus.providers.ProviderType
import kotlinx.android.synthetic.main.activity_account.*
import net.openid.appauth.*

class AccountActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var provider: BaseProvider

    override fun onClick(view: View) {
        val type=when(view.id){
          R.id.google-> ProviderType.GOOGLE
          R.id.dropbox->ProviderType.DROPBOX
          else->null
        }
        this.provider= BaseProvider.create(type!!)
        val request=provider.buildAuthRequest()
        val authIntent = authService.getAuthorizationRequestIntent(request)
        startActivityForResult(authIntent, 55)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 55) {

            if(provider is DropboxProvider){
                val url= Uri.parse(data?.data?.toString()?.replace("#","?"))
                var token =url.getQueryParameter("access_token")
             
            }else{
                val resp = AuthorizationResponse.fromIntent(data!!)
                val ex = AuthorizationException.fromIntent(data)
                resp?.authorizationCode?.let { code->
                    val request=resp.createTokenExchangeRequest()
                    //val request=provider.buildTokenRequest(code)
                    authService.performTokenRequest(request){response, e ->
                        print("")
                    }
                }
            }


        }
    }

    private lateinit var authService: AuthorizationService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        authService = AuthorizationService(this)
        google.setOnClickListener(this)
        amazon.setOnClickListener(this)
        dropbox.setOnClickListener(this)

    }
}

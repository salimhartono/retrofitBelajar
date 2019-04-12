package src.com.feature

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.bumptech.glide.Glide
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast
import src.com.feature.R.layout.activity_main
import src.com.feature.home.Home
import src.com.feature.manual_book.ManualBook
import src.com.feature.tamu.DaftarTamu
import java.util.*

class MainActivity : AppCompatActivity() {

    private var typeLogin = "1"
    private lateinit var callbackManager: CallbackManager
    private lateinit var loginButton: LoginButton
    private var nama = ""
    private var kelamin = ""
    private var email = ""
    private var photo = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)

        callbackManager = CallbackManager.Factory.create()

        //foto bg frame
        Glide.with(this)
            .load(getString(R.string.mainFrame))
            .into(ivDashBoard)


        btFacebook.setOnClickListener {
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email"))
            facebookLogin()
            if (nama.isNotEmpty()) {

            }

        }

        btBook.setOnClickListener {
            typeLogin = "2"
            startActivity(
                intentFor<ManualBook>()
                    .putExtra("typeLogin", typeLogin)
                    .putExtra("namaTamu", nama)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            )
        }

        lihatTamu.onClick {
            startActivity(
                intentFor<DaftarTamu>(
                ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            )
        }

    }

    private fun facebookLogin() {
        LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                handleAcces(result)
                nextActivity()
            }

            override fun onCancel() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onError(error: FacebookException?) {
            }

        })
    }

    private fun handleAcces(loginResult: LoginResult?) {
        val graphRequest = GraphRequest.newMeRequest(loginResult?.accessToken) { `object`, response ->
            try {
                Log.d("FACEBOOK TOKEN IKI LOH", `object`.toString())
                nama = `object`.getString("name")
                email = `object`.getString("email")
                var profile = Profile.getCurrentProfile()
                photo = "https://graph.facebook.com/" + profile.id + "/picture?width=400"
                toast(nama)
                toast(email)
                toast(photo)
                namasaya.text = nama
                if (Profile.getCurrentProfile() != null) {
                    Log.i("Login", "ProfilePic" + Profile.getCurrentProfile().getProfilePictureUri(200, 200));
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        val parameter = Bundle()
        parameter.putString("fields", "name,email,id,picture.type(large)")
        graphRequest.parameters = parameter
        graphRequest.executeAsync()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    private fun nextActivity() {
        namasaya.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (nama.isNotEmpty() || nama.length > 1) {
                    startActivity(
                        intentFor<Home>()
                            .putExtra("typeLogin", typeLogin)
                            .putExtra("namaTamu", nama)
                            .putExtra("email", email)
                            .putExtra("photo", photo)
                            .clearTop()
                    )
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })
    }

}

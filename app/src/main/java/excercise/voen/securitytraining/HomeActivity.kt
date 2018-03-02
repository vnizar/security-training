package excercise.voen.securitytraining

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

/**
 * Created by voen on 05/02/18.
 */
class HomeActivity : AppCompatActivity() {
    private val normalPref by lazy {
        getSharedPreferences("my-normal-preferences", Context.MODE_PRIVATE)
    }
    private val securePref by lazy {
        (application as MyApp).sharedPref
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        securePref.edit().putString("test_string","This is a testing string").apply()
        normalPref.edit().putString("normal_test_string","This is a normal testing string").apply()

        Log.d("voen","BASE_URL ${BuildConfig.BASE_URL}")
        if (checkAppSignature(this)) {
            Log.d("voen", "This app has valid   signature")
        } else {
            Log.d("voen", "This app don't have valid signature")
        }

        if (verifyInstaller(this)) {
            Log.d("voen", "This app installed from Google Playstore")
        } else {
            Log.d("voen", "This app installed from unknown source")
        }

        if (isEmulator()) {
            Log.d("voen", "This app run on emulator")
        } else {
            Log.d("voen", "This app run on device (maybe)")
        }

        if (isDebuggable(this)) {
            Log.d("voen", "This app is debuggable")
        } else {
            Log.d("voen", "This app is NOT debuggable")
        }
    }
}
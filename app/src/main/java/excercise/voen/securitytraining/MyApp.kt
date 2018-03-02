package excercise.voen.securitytraining

import android.app.Application
import com.facebook.stetho.Stetho
import com.securepreferences.SecurePreferences
import com.tozny.crypto.android.AesCbcWithIntegrity

/**
 * Created by voen on 04/02/18.
 */
class MyApp : Application() {
    val sharedPref by lazy {
        SecurePreferences(this, AesCbcWithIntegrity.generateKey(), "my-app-preferences")
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }
}
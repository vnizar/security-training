package excercise.voen.securitytraining

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Build
import android.util.Base64
import net.idik.lib.cipher.so.CipherClient
import java.security.MessageDigest

/**
 * Created by voen on 05/02/18.
 */
fun checkAppSignature(context: Context): Boolean {
    val packegeInfo = context.packageManager.getPackageInfo(context.packageName, PackageManager.GET_SIGNATURES)
    packegeInfo.signatures.forEach {
        val msgDiggest = MessageDigest.getInstance("SHA")
        msgDiggest.update(it.toByteArray())
        val currentCert = Base64.encodeToString(msgDiggest.digest(), Base64.DEFAULT)
        val cert = CipherClient.certificate()
        if (currentCert.trim() == cert.trim()) {
            return true
        }
    }
    return false
}

fun verifyInstaller(context: Context): Boolean {
    val installer = context.packageManager.getInstallerPackageName(context.packageName)
    installer?.let {
        return installer.startsWith("com.android.vending")
    }
    return false
}

fun isEmulator() = Build.FINGERPRINT.contains("generic")
        || Build.FINGERPRINT.contains("unknown")
        || Build.MODEL.contains("google_sdk")
        || Build.MODEL.contains("Emulator")
        || Build.MODEL.contains("Android SDK built for x86")
        || Build.MANUFACTURER.contains("Genymotion")
        || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
        || "google_sdk" == Build.PRODUCT
        || "goldfish" == Build.HARDWARE

fun isDebuggable(context: Context) = context.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0


import android.content.Context
import android.provider.Settings

class KeyboardValidation(private val context: Context) {

    private val validKeyboardIdentifiers = listOf(
        "com.android.inputmethod.latin",
        "com.google.android.inputmethod.latin",
        "com.google.android.tts"
        // ... add more identifiers
    )

    fun getKeyboardIdentifier(): String {
        val vendor = Settings.Secure.getString(
            this.context.contentResolver,
            Settings.Secure.DEFAULT_INPUT_METHOD
        )
        return vendor.split("/").firstOrNull() ?: ""
    }

    fun isKeyboardValid(): Boolean {
        val keyboardIdentifier = getKeyboardIdentifier()
        return validKeyboardIdentifiers.contains(keyboardIdentifier)
    }
}

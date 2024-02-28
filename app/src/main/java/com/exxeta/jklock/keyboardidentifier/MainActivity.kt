package com.exxeta.jklock.keyboardidentifier

import KeyboardValidation
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.exxeta.jklock.keyboardidentifier.ui.theme.KeyboardIdentifierTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KeyboardIdentifierTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    KeyboardValidationContent()
                }
            }
        }
    }
}

@Composable
fun KeyboardValidationContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )  {
        val context = LocalContext.current
        KeyboardIdentifier(context)
        Spacer(modifier = Modifier.height(16.dp))
        ValidationIndicator(context)
    }
}

@Composable
fun KeyboardIdentifier(context: Context) {
    val keyboardIdentifier = KeyboardValidation(context).getKeyboardIdentifier()
    Column {
        Text(
            text = "Keyboard Identifier:",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Text(
            text = keyboardIdentifier,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ValidationIndicator(context: Context) {
    val isValid = KeyboardValidation(context).isKeyboardValid()
    val text = if (isValid) "VALID" else "INVALID"
    val color = if (isValid) Color.Green else Color.Red

    Text(
        text = text,
        style = TextStyle(color = color, fontWeight = FontWeight.Bold),
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}
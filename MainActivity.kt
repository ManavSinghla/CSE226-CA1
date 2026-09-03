package com.example.cse226

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cse226.ui.theme.CSE226Theme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CSE226Theme {
                PaymentProcessing()
            }
        }
    }
}

@Composable
fun PaymentProcessing() {

    var isProcessing by remember { mutableStateOf(false) }
    var statusMessage by remember { mutableStateOf("Ready to pay") }

    LaunchedEffect(isProcessing) {
        if (isProcessing) {
            statusMessage = "Processing payment..."

            delay(3000)

            statusMessage = "Transaction Complete"
            isProcessing = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = statusMessage
        )

        Spacer(modifier = Modifier.height(20.dp))

        if (isProcessing) {

            CircularProgressIndicator()

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    isProcessing = false
                    statusMessage = "Payment Cancelled"
                }
            ) {
                Text("Cancel Payment")
            }

        } else {

            Button(
                onClick = {
                    isProcessing = true
                    statusMessage = "Starting payment..."
                }
            ) {
                Text("Pay Now")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CheckoutScreenPreview() {
    CSE226Theme {
        PaymentProcessing()
    }
}

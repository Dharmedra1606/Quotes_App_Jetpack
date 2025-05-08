package com.dharmendra.quotesappjetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dharmendra.quotesappjetpack.helper.DataManager
import com.dharmendra.quotesappjetpack.screens.Pages
import com.dharmendra.quotesappjetpack.screens.QuoteDetail
import com.dharmendra.quotesappjetpack.screens.QuotesList
import com.dharmendra.quotesappjetpack.ui.theme.QuotesAppJetpackTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()

        CoroutineScope(Dispatchers.IO).launch {
            delay(3000)
            DataManager.loadData(applicationContext)
            DataManager.isLoading.value = true
        }
        setContent {
            QuotesAppJetpackTheme {
                App()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun App() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            "Quotes App",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 25.sp,
        )
        if (!DataManager.isLoading.value) {
            Loading()
        } else {
            if (DataManager.currentPages.value == Pages.LISTING) {
                QuotesList(DataManager.data, onClick = {
                    DataManager.switchPage(it)
                })
            } else {
                QuoteDetail(DataManager.currentQuote!!)
                DataManager.currentPages.value = Pages.DETAIL
            }
        }
    }

}

@Composable
fun Loading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "Loading...",
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.ExtraBold),
            fontSize = 25.sp,
            modifier = Modifier.align(Alignment.Center)
                .padding(50.dp)


        )
    }
}


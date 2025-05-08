package com.dharmendra.quotesappjetpack.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.dharmendra.quotesappjetpack.models.Quote

@Composable
fun QuotesList(data: Array<Quote>, onClick: (quote: Quote) -> Unit){

    LazyColumn(
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        items(data.size) {
            ListItem(quote = data[it], onClick = onClick)
        }
    }

}
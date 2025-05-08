package com.dharmendra.quotesappjetpack.helper

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import com.dharmendra.quotesappjetpack.models.Quote
import com.dharmendra.quotesappjetpack.screens.Pages
import com.google.gson.Gson

object DataManager {

    var isLoading = mutableStateOf(false)
    var data = emptyArray<Quote>()
    var currentPages = mutableStateOf(Pages.LISTING)
    var currentQuote: Quote? = null

    fun loadData(context: Context){
        val inputStream = context.assets.open("qoutes.json")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        data = gson.fromJson(json, Array<Quote>::class.java)
    }

    fun switchPage(quote: Quote?){
        if (currentPages.value == Pages.LISTING){
            currentQuote = quote
            currentPages.value = Pages.DETAIL
        }else{
            currentPages.value = Pages.LISTING
        }
    }
}
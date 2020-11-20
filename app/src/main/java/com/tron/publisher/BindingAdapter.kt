package com.tron.publisher

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.lang.String.format
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("time")
fun getTimeStamp(textView: TextView, timeinMillies: Long) {
    timeinMillies?.let {
        textView.text = "${SimpleDateFormat("yyyy.MM.dd HH:mm").format(Date(timeinMillies))}"
    }
}

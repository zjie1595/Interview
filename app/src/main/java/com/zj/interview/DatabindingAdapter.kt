package com.zj.interview

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("android:china_date")
fun loadChinaDate(textView: TextView, time: Long) {
    val format = SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA)
    textView.text = format.format(time)
}
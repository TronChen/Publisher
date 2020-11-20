package com.tron.publisher

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class PublisherData(
    var author: HashMap<String,String> = hashMapOf(
        "email" to "",
        "id" to  "",
        "name" to ""),
    var title: String = "",
    var content: String = "",
    var createTime: Long = 0L,
    var id: String = "",
    var category: String = ""
):Parcelable


@Parcelize
data class PDResult(
    var author: Author? = null,
    var title: String = "",
    var content: String = "",
    var createTime: Long = 0L,
    var id: String = "",
    var category: String = ""
):Parcelable

@Parcelize
data class Author(
    var email: String = "",
    var id: String = "",
    var name: String = ""
):Parcelable
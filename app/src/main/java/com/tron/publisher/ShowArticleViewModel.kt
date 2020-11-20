package com.tron.publisher

import android.util.Log
import androidx.databinding.InverseMethod
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class ShowArticleViewModel: ViewModel() {


    val db = Firebase.firestore

    val _publisherData = MutableLiveData<List<PublisherData>>()
    val publisherData: LiveData<List<PublisherData>>
        get() = _publisherData

    val pdResult = MutableLiveData<List<PDResult>>()

    // status for the loading icon of swl
    val _refreshStatus = MutableLiveData<Boolean>()
    val refreshStatus: LiveData<Boolean>
        get() = _refreshStatus


    init {
        listenData()
    }


    private fun listenData(){
    db.collection("articles")
        .orderBy("createTime",Query.Direction.DESCENDING)
        .addSnapshotListener { value, _ ->
        value?.let { it ->
            _publisherData.value = it.toObjects(PublisherData::class.java)
            _refreshStatus.value = true
            _refreshStatus.value = false
        }
      }
    }

    var mutableListPDR = mutableListOf<PDResult>()
    fun getPDResult(){
        for (pd in _publisherData.value!!){
            val author = Author(
                name = pd.author["name"].toString(),
                email = pd.author["email"].toString(),
                id = pd.author["id"].toString()
            )
            val pdResult1 = PDResult(
                author = author,
                title = pd.title,
                content = pd.content,
                createTime = pd.createTime,
                id = pd.id,
                category = pd.category
            )
            mutableListPDR.add(pdResult1)
        }
            pdResult.value = mutableListPDR
        Log.e("pdResult.value","${pdResult.value}")
        Log.e("pdResult.valueaaaaaa","${mutableListPDR}")
    }


    fun initPDR() {
        mutableListPDR.removeAll(pdResult.value!!)
    }

}
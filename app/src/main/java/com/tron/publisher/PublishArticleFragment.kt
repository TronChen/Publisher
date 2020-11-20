package com.tron.publisher

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.tron.publisher.databinding.FragmentPublishArticleBinding
import java.util.*


class PublishArticleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentPublishArticleBinding.inflate(inflater,container,false)
        val articles = Firebase.firestore.collection("articles")
        val document = articles.document()


        binding.button.setOnClickListener {
            val title = binding.inputTitle.text.toString()
            val category = binding.inputCategory.text.toString()
            val content = binding.inputContent.text.toString()

            val author = hashMapOf(
                "email" to "dtp6284tj0@gmail.com",
                "id" to "trontron",
                "name" to "小大川"
            )
            val data = hashMapOf(
            "author" to author,
            "title" to title,
            "content" to content,
            "createTime" to Calendar.getInstance().timeInMillis,
            "id" to document.id,
            "category" to category
        )
            if (author["name"] == "" || author["email"] == "" || author["id"] == ""){
                Toast.makeText(context, "請登入唷", Toast.LENGTH_SHORT).show()
            }else{
            document.set(data)
                findNavController().navigate(R.id.action_global_showArticleFragment2)
            }
        }

        return binding.root
    }
}


package com.tron.publisher

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tron.publisher.databinding.FragmentShowArticleBinding


class ShowArticleFragment : Fragment() {

    private val viewModel: ShowArticleViewModel by lazy {
        ViewModelProvider(this).get(ShowArticleViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentShowArticleBinding.inflate(inflater,container,false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val adapter = ShowArticleAdapter()
        binding.recyclerview.adapter = adapter



            viewModel.publisherData.observe(viewLifecycleOwner, Observer {
                viewModel.getPDResult()
                adapter.submitList(viewModel.pdResult.value)
            })
                binding.layoutSwipeRefreshHome.setOnRefreshListener {
                    viewModel.getPDResult()
                }


        viewModel.refreshStatus.observe(viewLifecycleOwner, Observer {
            it?.let {
                    binding.layoutSwipeRefreshHome.isRefreshing = it
                Log.e("AAAAAAAAA",it.toString())
            }
        })


        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_global_publishArticleFragment)
            viewModel.initPDR()
        }

        return binding.root
    }

}
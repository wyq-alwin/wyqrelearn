package com.example.wyqrelearn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wyqrelearn.databinding.FragmentListBinding
import com.example.wyqrelearn.viewmodel.MainViewModel

class ListFragment : Fragment() {

    lateinit var binding: FragmentListBinding

//    val viewModel = activityViewModels<MainViewModel>()
        val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.recyclerview.layoutManager = LinearLayoutManager(context)
        context?.let {
            binding.recyclerview.adapter = RecyclerViewAdapter(it)
        }
    }

}
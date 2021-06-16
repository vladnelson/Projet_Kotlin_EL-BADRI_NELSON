package com.example.skyder.view.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.skyder.R
import com.example.skyder.domain.WeatherModel

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    var vm: HomeViewModel? = null
    var adapter: WeatherAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })


        vm = ViewModelProvider(this)[HomeViewModel::class.java]

        initAdapter()



        return root
    }


    private fun initAdapter() {
        adapter = WeatherAdapter(activity?.applicationContext)



    }
}
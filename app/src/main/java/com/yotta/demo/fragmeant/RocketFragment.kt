package com.yotta.demo.fragmeant

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yotta.demo.R
import com.yotta.demo.`class`.RocketRepository
import com.yotta.demo.`class`.RocketViewModelFactory
import com.yotta.demo.adapter.RocketAdapter
import com.yotta.demo.network.RocketApi

class RocketFragment : Fragment() {

    private lateinit var viewModel: RocketViewModel
    private lateinit var factory: RocketViewModelFactory
    lateinit var  recycler_view_movies : RecyclerView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_rocket, container, false)

        recycler_view_movies = view.findViewById(R.id.recycler_view_rockets)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val api = RocketApi()
        val repositry = RocketRepository(api)
        factory = RocketViewModelFactory(repositry)
        viewModel = ViewModelProvider(this,factory).get(RocketViewModel::class.java)
        viewModel.getRockets()
        val id = id;
        viewModel.rockets.observe(viewLifecycleOwner, Observer { rockets ->
            recycler_view_movies.also{
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = RocketAdapter(rockets,activity,id)
            }
        })
    }

}
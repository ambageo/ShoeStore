package com.udacity.shoestore.screens.shoelist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.ShoeViewModel
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import timber.log.Timber

class ShoeListFragment : Fragment() {

    //private lateinit var viewModel: ShoeViewModel
    private val viewModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentShoeListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)

        //viewModel = ViewModelProvider(this).get(ShoeViewModel::class.java)

        viewModel.shoeList.observe(viewLifecycleOwner, Observer { shoeList ->
            if(shoeList.isNotEmpty()){
                Timber.i("list is not empty, has ${shoeList.size} shoes")
            } else {
                Timber.i("list is empty")
            }
            for( shoe in shoeList){
                Timber.i("Shoe: ${shoe.name} ${shoe.company} ${shoe.size} ${shoe.description}")
            }
        })
        binding.fabAddShoe.setOnClickListener { view: View ->
            view.findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }
        return binding.root
    }
}
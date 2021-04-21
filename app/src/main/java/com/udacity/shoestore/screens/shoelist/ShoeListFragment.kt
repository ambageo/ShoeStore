package com.udacity.shoestore.screens.shoelist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.ShoeViewModel
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ShoeDetailsCardBinding

class ShoeListFragment : Fragment() {

    private val viewModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentShoeListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)
        binding.setLifecycleOwner(this)

        viewModel.shoeList.observe(viewLifecycleOwner, Observer { shoeList ->
            for( shoe in shoeList){
                val shoeBinding: ShoeDetailsCardBinding = DataBindingUtil.inflate(inflater, R.layout.shoe_details_card, binding.shoeListLinearlayout, false)
               // No need to add each attribute of the shoe, just setting the shoe to the data binding
                shoeBinding.shoe = shoe
                binding.shoeListLinearlayout.addView(shoeBinding.root)
            }
        })
        binding.fabAddShoe.setOnClickListener { view: View ->
            view.findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }
        return binding.root
    }

}
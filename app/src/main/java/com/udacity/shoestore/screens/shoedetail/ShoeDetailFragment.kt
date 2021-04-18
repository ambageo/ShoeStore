package com.udacity.shoestore.screens.shoedetail

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.ShoeViewModel
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeDetailFragment : Fragment() {

    //private val viewModel: ShoeViewModel by ActivityV
    private val viewModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentShoeDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail,container, false)

        //viewModel = ViewModelProvider(this).get(ShoeViewModel::class.java)

        binding.setLifecycleOwner(this)
        binding.shoeViewModel = viewModel

        viewModel.eventNavigateBackToShoeList.observe(viewLifecycleOwner, Observer { shouldNavigateBack ->
            if(shouldNavigateBack){
                findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
                viewModel.onNavigateBackToShoeListComplete()
            }

            viewModel.eventShowToast.observe(viewLifecycleOwner, Observer { shouldShowToast ->
                if(shouldShowToast){
                    Toast.makeText(requireContext(), R.string.fill_all_fields_message, Toast.LENGTH_SHORT).show()
                    viewModel.onShowedToastComplete()
                }
            })

        })
        binding.saveButton.setOnClickListener { view:View ->
                view.findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
        }
        binding.cancelButton.setOnClickListener { view:View ->
            view.findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
        }
        setHasOptionsMenu(true)

        return binding.root
    }

    // This hides the menu because we only want to show it on the Shoe Listing Screen
    override fun onPrepareOptionsMenu(menu: Menu) {
        val item: MenuItem = menu.findItem(R.id.loginFragment)
        item.isVisible = false
    }
}
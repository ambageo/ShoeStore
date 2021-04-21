package com.udacity.shoestore.screens.welcome

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentWelcomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_welcome, container, false)
        binding.instructionsButton.setOnClickListener { view: View ->
            view.findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToInstructionsFragment())
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
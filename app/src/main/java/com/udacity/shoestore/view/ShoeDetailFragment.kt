package com.udacity.shoestore.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewmodels.ShoeViewModel

class ShoeDetailFragment : Fragment() {
    private val viewModel: ShoeViewModel by activityViewModels()
    lateinit var binding: FragmentShoeDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_detail, container, false
        )

        val args = ShoeDetailFragmentArgs.fromBundle(requireArguments())
        binding.shoe = args.shoe

        binding.cancelButton.setOnClickListener { view: View ->
            view.findNavController().popBackStack()
        }
        binding.saveButton.setOnClickListener { view: View ->
            addNewShoe(view)
        }
        return binding.root
    }

    private fun addNewShoe(view: View) {
        val newShoe = Shoe(
            binding.nameEdit.text.toString(),
            binding.shoeSizeEdit.text.toString().toDouble(),
            binding.companyEdit.text.toString(),
            binding.descriptionEdit.text.toString(),
            mutableListOf()
        )
        viewModel.addShoe(newShoe)
        view.findNavController().popBackStack()
    }

}
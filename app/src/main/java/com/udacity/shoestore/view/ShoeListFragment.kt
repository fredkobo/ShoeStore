package com.udacity.shoestore.view

import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewmodels.ShoeViewModel

class ShoeListFragment : Fragment() {

    private val viewModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentShoeListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_list, container, false
        )
        binding.addFloatingButton.setOnClickListener { view: View ->
            view.findNavController().navigate(
                ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment(
                    null
                )
            )
        }

        viewModel.shoeList.observe(viewLifecycleOwner, Observer { shoeList ->
            binding.contentLayout.removeAllViews()
            for ((index, shoe) in shoeList.withIndex()) {
                binding.contentLayout.addView(getShoeItemView(shoe, index))
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    private fun getShoeItemView(shoe: Shoe, position: Int): TextView {
        val textView = TextView(context)
        textView.text = shoe.name
        textView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        )
        textView.setTextSize(
            TypedValue.COMPLEX_UNIT_PX,
            resources.getDimension(R.dimen.list_item_text_size)
        )
        val padding = resources.getDimensionPixelOffset(R.dimen.list_item_padding)
        textView.setPadding(padding, padding, padding, padding)
        context?.let {

        }
        val backgroundColor = if (position % 2 == 0) {
            ContextCompat.getColor(requireContext(), R.color.colorPrimaryVeryLight)
        } else {
            ContextCompat.getColor(requireContext(), R.color.colorAccentVeyLight)
        }
        textView.setBackgroundColor(backgroundColor)
        textView.setTextColor(Color.BLACK)
        textView.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(
                    ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment(
                        shoe
                    )
                )
        }
        return textView
    }
}
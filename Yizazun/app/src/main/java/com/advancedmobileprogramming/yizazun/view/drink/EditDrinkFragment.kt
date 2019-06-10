package com.advancedmobileprogramming.yizazun.view.drink

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.advancedmobileprogramming.yizazun.R
import com.advancedmobileprogramming.yizazun.viewmodel.drink.EditDrinkViewModel

class EditDrinkFragment : Fragment() {

    companion object {
        fun newInstance() = EditDrinkFragment()
    }

    private lateinit var viewModel: EditDrinkViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.edit_drink_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EditDrinkViewModel::class.java)
        // TODO: Use the ViewModel
    }

}

package com.advancedmobileprogramming.yizazun.view.food

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.advancedmobileprogramming.yizazun.R
import com.advancedmobileprogramming.yizazun.viewmodel.food.EditFoodViewModel

class EditFoodFragment : Fragment() {

    companion object {
        fun newInstance() = EditFoodFragment()
    }

    private lateinit var viewModel: EditFoodViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.edit_food_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EditFoodViewModel::class.java)
        // TODO: Use the ViewModel
    }

}

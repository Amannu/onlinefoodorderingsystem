package com.example.yizazun

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation





class EditFoodFragment : Fragment() {
    private lateinit var binding:FragmentEditNewsBinding
    private lateinit var viewModel: FoodViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_food, container, false)
        viewModel = ViewModelProviders.of(this).get(ItemViewModel::class.java)
        binding.viewModel = viewModel
        arguments?.let {
            viewModel.getFoodById(it.getInt("argId")).observe(this, Observer {
                    news->news?.let {
                viewModel.headline.value = it.title
                viewModel.content.value = it.content
                viewModel.nid.value = it.id
            }
            })
        }
        binding.lifecycleOwner = this
        getToolbarNavigationButton()?.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_editFoodFragment_to_foodFragment)
        }
        return binding.root
    }
    fun getToolbarNavigationButton(): ImageButton?{
        val size = binding.toolbar4.childCount
        for (i in 0..size){
            val child = binding.toolbar4.getChildAt(i)
            if(child is ImageButton){
                val imgButton = child
                if(imgButton.drawable == binding.toolbar4.navigationIcon){
                    return imgButton
                }
            }
        }
        return null
    }



}
package com.advancedmobileprogramming.yizazun.view.user

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.advancedmobileprogramming.yizazun.R
import com.advancedmobileprogramming.yizazun.viewmodel.user.EditUserViewModel

class EditUserFragment : Fragment() {

    companion object {
        fun newInstance() = EditUserFragment()
    }

    private lateinit var viewModel: EditUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.edit_user_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EditUserViewModel::class.java)
        // TODO: Use the ViewModel
    }

}

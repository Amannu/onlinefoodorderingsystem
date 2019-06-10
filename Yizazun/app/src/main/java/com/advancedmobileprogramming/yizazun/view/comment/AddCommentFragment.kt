package com.advancedmobileprogramming.yizazun.view.comment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.advancedmobileprogramming.yizazun.R
import com.advancedmobileprogramming.yizazun.viewmodel.comment.AddCommentViewModel

class AddCommentFragment : Fragment() {

    companion object {
        fun newInstance() = AddCommentFragment()
    }

    private lateinit var viewModel: AddCommentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_comment_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AddCommentViewModel::class.java)
        // TODO: Use the ViewModel
    }

}

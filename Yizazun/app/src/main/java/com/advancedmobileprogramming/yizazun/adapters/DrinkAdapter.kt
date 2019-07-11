package com.example.yizazun.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.yizazun.R
import com.example.yizazun.data.entity.Drink
import com.example.yizazun.interfaces.DrinkProvider

class DrinkAdapter(val context: Context): RecyclerView.Adapter<DrinkAdapter.DrinkViewHolder>(),DrinkProvider {
    private var drink:List<Drink> = emptyList()
    private var viewModel:DrinkViewModel? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkViewHolder {
        val view:RecylcerDrinkBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.recylcer_drink,parent,false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return drink.size
    }

    override fun onBindViewHolder(holder: DrinkViewHolder, position: Int) {
        val n = drink[position]
        holder.recyclerDrinkBinding.data = n
        holder.recyclerDrinkBinding.executePendingBindings()
        holder.recyclerNewsBinding.newsClickListener = this
    }

    internal fun setNews(newsList:List<News>){
        this.news = newsList
        notifyDataSetChanged()
    }
    internal fun setViewModel(viewModel: NewsViewModel){
        this.viewModel = viewModel
    }

    class NewsViewHolder(itemView: RecylcerAuthorNewsBinding): RecyclerView.ViewHolder(itemView.root){
        val recyclerNewsBinding:RecylcerAuthorNewsBinding = itemView
    }

    override fun onDeleteClick(news: News) {
        val alert = AlertDialog.Builder(context)
        alert.setTitle(context.getString(R.string.delete_text))
        alert.setMessage(context.getString(R.string.are_you_text))
        alert.setPositiveButton(context.getString(R.string.ok_text)){dialog, which ->
            viewModel?.deleteNews(news)
            Toast.makeText(context,"News Deleted Successfully", Toast.LENGTH_SHORT).show()
        }
        alert.setNegativeButton(context.getString(R.string.cancel)){dialog,which ->
            dialog.dismiss()
        }
        alert.setCancelable(false)
        val builder = alert.create()
        builder.show()
    }

    override fun onEditClick(view: View, news: News) {
        var action = AuthorFragmentDirections.actionAuthorFragmentToEditNewsFragment()
        action.argId = news.id
        Navigation.findNavController(view).navigate(action)
    }
}
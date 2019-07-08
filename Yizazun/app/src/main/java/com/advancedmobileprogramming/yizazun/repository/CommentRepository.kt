package com.advancedmobileprogramming.yizazun.repository

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.lifecycle.LiveData
import com.advancedmobileprogramming.yizazun.model.dao.CommentDao
import com.advancedmobileprogramming.yizazun.model.entities.Comment
import com.advancedmobileprogramming.yizazun.model.network.CommentApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

class CommentRepository (private val commentDao: CommentDao, private val commentApiService: CommentApiService, private val application: Application){
    suspend fun getCommentById(id:Long): LiveData<Comment> = commentDao.getCommentById(id)

    suspend fun getAllComment(): LiveData<List<Comment>> {

        val comment = commentDao.getComments().value
        // if(comment == null || comment.isEmpty()){
        refreshAllComments()
        // }
        return commentDao.getComments()
    }

    suspend fun insertComments(comment: Comment) {
        val connectivityManager=  application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo:NetworkInfo=connectivityManager.activeNetworkInfo


        if(networkInfo?.isConnected){
            commentApiService.postComments(comment)
            commentDao.insertComment(comment)
        }

    }

    suspend fun deleteComment(comment: Comment){
        val connectivityManager=  application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo:NetworkInfo=connectivityManager.activeNetworkInfo


        if(networkInfo?.isConnected){
            commentApiService.deleteComments(comment.id)
            commentDao.deleteCommentById(comment)
        }
    }

    suspend fun updateComment(comment: Comment){
        val connectivityManager=  application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo:NetworkInfo=connectivityManager.activeNetworkInfo


        if(networkInfo?.isConnected){
            commentApiService.updateComments(comment.id,comment)
            commentDao.updateComment(comment)
        }
    }
    fun refreshAllComments(){
        val connectivityManager=  application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo =connectivityManager.activeNetworkInfo


        if(networkInfo?.isConnected){
            try {
                GlobalScope.launch(Dispatchers.IO) {
                    val response: Response<List<Comment>> = commentApiService.getComments().await()
                    val comments:List<Comment>? = response.body()
                    if(comments!=null){
                        for (comment in comments){
                            commentDao.insertComment(comment)
                        }
                    }
                }
            }catch (e: Exception){

            }
        }

    }
}
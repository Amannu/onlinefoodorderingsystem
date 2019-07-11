package com.example.yizazun.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.example.yizazun.data.dao.AnnouncementDao
import com.example.yizazun.data.entity.Announcement
import com.example.yizazun.services.AnnouncementApiServices
import com.example.yizazun.utility.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception

class AnnouncementRepository (private  val announcementDao: AnnouncementDao, private  val announcementApiServices: AnnouncementApiServices, private  val application: Application){
    fun getAnnouncementById(id:Int): LiveData<Announcement> {
        return announcementDao.getAnnouncementById(id)
    }
    fun getAllAnnouncement(): LiveData<List<Announcement>> {

        //Check if we are opining the app for first time and the database is null or empty
        //This part is done only once as you told me in the office

        val announcement = announcementDao.getAnnouncements().value
        if(announcement == null || announcement.isEmpty()){
            refreshAllAnnouncement()
        }
        return announcementDao.getAnnouncements()
    }

    fun insertAnnouncement(announcement: Announcement){
        announcementApiServices.insertAnnouncementAsync(announcement)
    }

    fun updateAnnouncement(announcement: Announcement){
        announcementApiServices.updateAnnouncementsAsync(announcement.id,announcement)
        announcementDao.updateAnnouncement(announcement)
    }

    fun deleteAnnouncement(announcement:Announcement){
        if(Constant.connected(application)){
            announcementApiServices.deleteAnnouncementsByIdAsync(announcement.id)
            announcementDao.deleteAnnouncement(announcement)
        }else{
            GlobalScope.launch(Dispatchers.Main) {
                Toast.makeText(application,"Your are not connected", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun refreshAllAnnouncement(){
        if(Constant.connected(application)){
            try {
                GlobalScope.launch(Dispatchers.IO) {
                    val response: Response<List<Announcement>> = announcementApiServices.findAllAnnouncementsAsync().await()
                    val announcements:List<Announcement>? = response.body()
                    if(announcements!=null){
                        for (f in announcements){
                            announcementDao.insertAnnouncement(f)
                        }
                    }
                }
            }catch (e: Exception){

            }
        }

    }
}
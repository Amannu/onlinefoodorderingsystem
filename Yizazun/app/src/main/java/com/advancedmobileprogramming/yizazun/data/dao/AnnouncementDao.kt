package com.example.yizazun.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.yizazun.data.entity.Announcement

@Dao
interface AnnouncementDao {

        @Query("SELECT * FROM announcement WHERE id = :id")
        fun getAnnouncementById(id:Int): LiveData<Announcement>

        @Query("SELECT * FROM announcement")
        fun getAnnouncements(): LiveData<List<Announcement>>

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        fun insertAnnouncement(announcement: Announcement)

        @Update
        fun updateAnnouncement(announcement: Announcement)

        @Delete
        fun deleteAnnouncement(announcement: Announcement)
}

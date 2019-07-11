package com.example.yizazun.interfaces

import android.view.View
import com.example.yizazun.data.entity.Announcement

interface AnnouncementProvider {
    fun onEditClick(view: View, announcement: Announcement)
    fun onDeleteClick(announcement: Announcement)
}
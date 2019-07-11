package com.example.Yizazun

import com.example.Yizazun.data.Announcement
import org.junit.Assert.*
import org.junit.Test
import java.util.*

class InsertAnnouncementTest{

    @Test
    fun validInsertAnnouncementFields(){
        var announcement= Announcement(0,"http://test_path","name","test type","test desc",12,
            12.12, Date().toString(),"@testUsername")

        var isValid=AnnouncementInsertFragment.allAnnouncementFieldsSpecified(announcement)
        assert(isValid)
    }
}
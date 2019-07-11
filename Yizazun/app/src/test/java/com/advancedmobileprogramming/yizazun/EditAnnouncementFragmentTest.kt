package com.example.Yizazun

import com.example.Yizazun.data.Announcement
import com.example.Yizazun.data.User
import org.junit.Assert.*
import org.junit.Test
import java.util.*

class EditAnnouncementFragmentTest{
    @Test
    fun validEditedAnnouncementFields(){
        var announcement=Announcement(0,"http://test_path","name","test type","test desc",12,
            12.12,Date().toString(),"@testUsername")

        var isValid=EditAnnouncementFragment.editedAnnouncementFields(announcement)

        assert(isValid)
    }
}
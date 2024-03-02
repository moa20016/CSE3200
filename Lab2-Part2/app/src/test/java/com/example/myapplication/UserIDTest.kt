package com.example.myapplication
import com.example.myapplication.model.UserID
import org.junit.Assert.assertEquals
import org.junit.Test

class UserIDTest {

    @Test
    fun testGetName() {
        val userID = UserID()
        userID.setName("John Doe")
        assertEquals("John Doe", userID.getName())
    }

    @Test
    fun testSetName() {
        val userID = UserID()
        userID.setName("Jane Doe")
        assertEquals("Jane Doe", userID.getName())
    }
}
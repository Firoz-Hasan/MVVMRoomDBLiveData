package com.fhd.roomdblivedatamvvm.model.repository.roomDB

import androidx.lifecycle.LiveData
import androidx.room.*

/*
dao =  where we define all the db operations tht we want to make WORD entity
* eikhane insert update delete kora hosse table e
*A DAO (data access object) validates your SQL at compile-time and associate it with a method,
* so you don't have to worry about the SQL again... all with simple annotations like @Insert!
*
* */

@Dao
interface ContactDao {
    @Insert
    fun insert(Contact: Contact)

    @Update
    fun update(Contact: Contact)

    @Delete
    fun delete(Contact: Contact)

    @Query("DELETE FROM contact_table")
    fun deleteAllNotes()

    @Query("SELECT * FROM contact_table")
    fun getAllContacts(): LiveData<List<Contact>>
}
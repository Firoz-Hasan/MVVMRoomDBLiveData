package com.fhd.roomdblivedatamvvm.model.roomdb

import androidx.lifecycle.LiveData
import androidx.room.*

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
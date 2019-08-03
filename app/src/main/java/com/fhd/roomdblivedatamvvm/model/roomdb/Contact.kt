package com.fhd.roomdblivedatamvvm.model.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact_table")
data class Contact(
    var contact_name: String,
    var contact_phoneNumber: String
) {
    //does it matter if these are private or not?
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}


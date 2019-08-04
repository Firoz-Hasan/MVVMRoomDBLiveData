package com.fhd.roomdblivedatamvvm.model.repository.roomDB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact_table")
data class Contact(
    var contact_name_db: String,
    var contact_phoneNumber_db: String
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}


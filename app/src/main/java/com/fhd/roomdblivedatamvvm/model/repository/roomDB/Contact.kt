package com.fhd.roomdblivedatamvvm.model.repository.roomDB

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
* data class : We frequently create classes whose main purpose is to hold data
* eikhane (entitiy means = table) banano hoise, table e column bosano hoise
* Each @Entity class represents a table.
*
*
* */

@Entity(tableName = "contact_table")
data class Contact(
    var contact_name_db: String,
    var contact_phoneNumber_db: String
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}


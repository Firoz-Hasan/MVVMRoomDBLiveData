package com.fhd.roomdblivedatamvvm.model.repository.roomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = [Contact::class], version = 1)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao


    companion object {
        private var instance: ContactDatabase? = null

        fun getInstance(context: Context): ContactDatabase? {
            if (instance == null) {
                synchronized(ContactDatabase::class) {
                    //why synchronized? bec one thread at a time can only access this method
                    //in multithread environment two different threads can try to access
                    //at the same time so synchronized prohibit/avoid this.
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ContactDatabase::class.java, "note_database"
                    )
                        .fallbackToDestructiveMigration() // when version increments, it migrates (deletes db and creates new) - else it crashes
                        .addCallback(roomCallback)
                        .build()
                }
            }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }

        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
               // PopulateDbAsyncTask(instance).execute()
            }
        }
    }
}
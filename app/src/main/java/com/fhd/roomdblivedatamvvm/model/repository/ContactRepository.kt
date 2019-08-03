package com.fhd.roomdblivedatamvvm.model.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.fhd.roomdblivedatamvvm.model.roomdb.Contact
import com.fhd.roomdblivedatamvvm.model.roomdb.ContactDao
import com.fhd.roomdblivedatamvvm.model.roomdb.ContactDatabase

class ContactRepository(application: Application) {

    private var contactDao: ContactDao

    private var allcontacts: LiveData<List<Contact>>
    init {
        val database: ContactDatabase = ContactDatabase.getInstance(
            application.applicationContext
        )!!
        contactDao = database.contactDao()
        allcontacts = contactDao.getAllContacts()
    }

    fun insert(note: Contact) {
        val insertContactAsyncTask = InsertContactAsyncTask(contactDao).execute(note)
    }

    fun update(note: Contact) {
        val updateContactAsyncTask = UpdateContactAsyncTask(contactDao).execute(note)
    }


    fun delete(note: Contact) {
        val deleteContactAsyncTask = DeleteContactAsyncTask(contactDao).execute(note)
    }

    fun deleteAllContacts() {
        val deleteAllContactAsyncTask = DeleteAllContactAsyncTask(
            contactDao
        ).execute()
    }

    fun getAllNotes(): LiveData<List<Contact>> {
        return allcontacts
    }

    companion object {
        private class InsertContactAsyncTask(contactDao: ContactDao) : AsyncTask<Contact, Unit, Unit>() {
            val contactDao = contactDao

            override fun doInBackground(vararg p0: Contact?) {
                contactDao.insert(p0[0]!!)
            }
        }

        private class UpdateContactAsyncTask(contactDao: ContactDao) : AsyncTask<Contact, Unit, Unit>() {
            val contactDao = contactDao

            override fun doInBackground(vararg p0: Contact?) {
                contactDao.update(p0[0]!!)
            }
        }

        private class DeleteContactAsyncTask(contactDao: ContactDao) : AsyncTask<Contact, Unit, Unit>() {
            val contactDao = contactDao

            override fun doInBackground(vararg p0: Contact?) {
                contactDao.delete(p0[0]!!)
            }
        }

        private class DeleteAllContactAsyncTask(contactDao: ContactDao) : AsyncTask<Unit, Unit, Unit>() {
            val contactDao = contactDao

            override fun doInBackground(vararg p0: Unit?) {
                contactDao.deleteAllNotes()
            }
        }
    }
}
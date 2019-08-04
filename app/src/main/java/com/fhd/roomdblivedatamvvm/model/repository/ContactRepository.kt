package com.fhd.roomdblivedatamvvm.model.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.fhd.roomdblivedatamvvm.model.repository.roomDB.Contact
import com.fhd.roomdblivedatamvvm.model.repository.roomDB.ContactDao
import com.fhd.roomdblivedatamvvm.model.repository.roomDB.ContactDatabase

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

    fun insert(contact: Contact) {
        val insertContactAsyncTask = InsertContactAsyncTask(contactDao).execute(contact)
    }

    fun update(contact: Contact) {
        val updateContactAsyncTask = UpdateContactAsyncTask(contactDao).execute(contact)
    }


    fun delete(contact: Contact) {
        val deleteContactAsyncTask = DeleteContactAsyncTask(contactDao).execute(contact)
    }

    fun deleteAllContacts() {
        val deleteAllContactAsyncTask = DeleteAllContactAsyncTask(
            contactDao
        ).execute()
    }

    fun getAllContacts(): LiveData<List<Contact>> {
        return allcontacts
    }

    companion object {

        // room doesnt allow db operation in main thread , so we hv to use background thread
        // if u write it in main thread the system will crush
        

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
package com.fhd.roomdblivedatamvvm.view.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.fhd.roomdblivedatamvvm.R
import com.fhd.roomdblivedatamvvm.model.repository.roomDB.Contact
import com.fhd.roomdblivedatamvvm.view.adapters.ContactAdapter
import com.fhd.roomdblivedatamvvm.viewmodel.ContactViewModel
import kotlinx.android.synthetic.main.activity_main.*

/*
* What is ROOM db?
* db layer top of sqlite/ provide an abstraction layer over sqlite to allow
* fluent database access
* Room will map our db object to java object
* COMPONENTS OF ROOM :
* Entity : defines schema of db table   / a representation of a plan for db table
* DAO : contains method to access db meaning provide API for reading & writting
* into db.
* Database : db holder class which serves as a main access point
*
*
* */
class MainActivity : AppCompatActivity() {

    companion object {
        const val ADD_NOTE_REQUEST = 1
        const val EDIT_NOTE_REQUEST = 2
    }

    private lateinit var contactViewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab.setOnClickListener {
            startActivityForResult(
                Intent(this, AddEditContactActivity::class.java),
                ADD_NOTE_REQUEST
            )
        }

        contactRV.layoutManager = LinearLayoutManager(this)
        contactRV.setHasFixedSize(true)

        var adapter = ContactAdapter()

        contactRV.adapter = adapter

        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel::class.java)

        contactViewModel.getAllContacts().observe(this, Observer<List<Contact>> {
            adapter.submitList(it)
        })


        /*ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT.or(ItemTouchHelper.RIGHT)) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                noteViewModel.delete(adapter.getNoteAt(viewHolder.adapterPosition))
                Toast.makeText(baseContext, "Note Deleted!", Toast.LENGTH_SHORT).show()
            }
        }
        ).attachToRecyclerView(contactRV)

        adapter.setOnItemClickListener(object : NoteAdapter.OnItemClickListener {
            override fun onItemClick(note: Note) {
                var intent = Intent(baseContext, AddEditContactActivity::class.java)
                intent.putExtra(AddEditContactActivity.EXTRA_ID, note.id)
                intent.putExtra(AddEditContactActivity.EXTRA_TITLE, note.title)
                intent.putExtra(AddEditContactActivity.EXTRA_DESCRIPTION, note.description)
                intent.putExtra(AddEditContactActivity.EXTRA_PRIORITY, note.priority)

                startActivityForResult(intent, EDIT_NOTE_REQUEST)
            }
        })
*/
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.delete_all_notes -> {
                contactViewModel.deleteAllContacts()
                Toast.makeText(this, "All notes deleted!", Toast.LENGTH_SHORT).show()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_NOTE_REQUEST && resultCode == Activity.RESULT_OK) {
            val newContact = Contact(
                data!!.getStringExtra(AddEditContactActivity.EXTRA_CONTACTNAME),
                data.getStringExtra(AddEditContactActivity.EXTRA_CONTACTPHONE)

            )
            contactViewModel.insert(newContact)

            Toast.makeText(this, "Note saved!", Toast.LENGTH_SHORT).show()
        } else if (requestCode == EDIT_NOTE_REQUEST && resultCode == Activity.RESULT_OK) {
            val id = data?.getIntExtra(AddEditContactActivity.EXTRA_ID, -1)

            if (id == -1) {
                Toast.makeText(this, "Could not update! Error!", Toast.LENGTH_SHORT).show()
            }

            val updateContact = Contact(
                data!!.getStringExtra(AddEditContactActivity.EXTRA_CONTACTNAME),
                data.getStringExtra(AddEditContactActivity.EXTRA_CONTACTPHONE)

            )
            updateContact.id = data.getIntExtra(AddEditContactActivity.EXTRA_ID, -1)
            contactViewModel.update(updateContact)

        } else {
            Toast.makeText(this, "Note not saved!", Toast.LENGTH_SHORT).show()
        }


    }


}

package com.fhd.roomdblivedatamvvm.view.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fhd.roomdblivedatamvvm.R
import kotlinx.android.synthetic.main.activity_add_contact.*

class AddEditContactActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ID = "com.fhd.EXTRA_ID"
        const val EXTRA_CONTACTNAME = "com.fhd.EXTRA_CONTACTNAME"
        const val EXTRA_CONTACTPHONE = "com.fhd.EXTRA_CONTACTPHONE"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)

        if (intent.hasExtra(EXTRA_ID)) {
            title = "Edit Contact"
            contactName_ET.setText(intent.getStringExtra(EXTRA_CONTACTNAME))
            contactNumber_ET.setText(intent.getStringExtra(EXTRA_CONTACTPHONE))
             } else {
            title = "Add Contact"
        }
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_contact_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.save_note -> {
                saveContact()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveContact() {
        if (contactName_ET.text.toString().trim().isBlank() ||
            contactNumber_ET.text.toString().trim().isBlank()) {
            Toast.makeText(this, "Can not insert empty note!", Toast.LENGTH_SHORT).show()
            return
        }

        val data = Intent().apply {
            putExtra(EXTRA_CONTACTNAME, contactName_ET.text.toString())
            putExtra(EXTRA_CONTACTPHONE, contactNumber_ET.text.toString())
            if (intent.getIntExtra(EXTRA_ID, -1) != -1) {
                putExtra(EXTRA_ID, intent.getIntExtra(EXTRA_ID, -1))
            }
        }

        setResult(Activity.RESULT_OK, data)
        finish()
    }
}
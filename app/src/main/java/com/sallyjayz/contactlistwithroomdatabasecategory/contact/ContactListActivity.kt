package com.sallyjayz.contactlistwithroomdatabasecategory.contact

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sallyjayz.contactlistwithroomdatabasecategory.Application
import com.sallyjayz.contactlistwithroomdatabasecategory.R
import com.sallyjayz.contactlistwithroomdatabasecategory.adapter.ContactListAdapter
import com.sallyjayz.contactlistwithroomdatabasecategory.adapter.OnItemClickListener
import com.sallyjayz.contactlistwithroomdatabasecategory.category.CategoryActivity.Companion.EXTRA_CATEGORY
import com.sallyjayz.contactlistwithroomdatabasecategory.model.ContactDetail

class ContactListActivity : AppCompatActivity(){

    private val newContactActivityRequestCode = 1
    private val categoryRequestCode = 2

    private val contactViewModel: ContactViewModel by viewModels  {
        ContactViewModelFactory((application as Application).contactRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_list)

        // Get the Intent that started this activity and extract the string
        val categoryReceived = intent.getStringExtra(EXTRA_CATEGORY)
        val actionBar = supportActionBar
        actionBar!!.title = categoryReceived
        actionBar.setDisplayHomeAsUpEnabled(true)


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = ContactListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        if (categoryReceived != null) {
            contactViewModel.findByCategory(categoryReceived)
            Toast.makeText(this,"$categoryReceived",Toast.LENGTH_LONG)
                .show()
        }
        contactViewModel.category.observe(this, Observer { contact ->
            contact?.let { adapter.submitList(it) }
            adapter.notifyDataSetChanged()
            Log.d("Userviewmodel", "findFilledEmailPassword: $contact")
        })

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@ContactListActivity, AddContactActivity::class.java).apply {
                putExtra(EXTRA_CATEGORY_RECEIVED, categoryReceived)
            }
            startActivityForResult(intent, newContactActivityRequestCode)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newContactActivityRequestCode && resultCode == Activity.RESULT_OK) {
            /*data?.getStringExtra(NewContactActivity.EXTRA_NAME)?.let {
                val contact = Contact(it)
                contactViewModel.insert(contact)
            }*/
            data?.getStringArrayExtra(AddContactActivity.EXTRA_VALUES)?.let {
                val contact = ContactDetail(it[0], it[1], it[2])
                contactViewModel.insert(contact)
            }
        } else {
            Toast.makeText(
                applicationContext,
                getString(R.string.empty_not_saved),
                Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        const val EXTRA_CATEGORY_RECEIVED = "com.sallyjayz.contactlistwithroomdatabase.CATEGORYRECEIVED"
    }

}
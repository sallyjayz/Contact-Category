package com.sallyjayz.contactlistwithroomdatabasecategory.contact

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
import com.sallyjayz.contactlistwithroomdatabasecategory.R
import com.sallyjayz.contactlistwithroomdatabasecategory.contact.ContactListActivity.Companion.EXTRA_CATEGORY_RECEIVED

class AddContactActivity : AppCompatActivity() {

    private lateinit var editNameView: TextInputEditText
    private lateinit var editNumberView: TextInputEditText
    private lateinit var textCategoryView: MaterialTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val categoryReceived = intent.getStringExtra(EXTRA_CATEGORY_RECEIVED)
        if (categoryReceived != null) {
            Toast.makeText(this,"$categoryReceived", Toast.LENGTH_LONG)
                .show()
        }

        editNameView = findViewById(R.id.nameEt)
        editNumberView = findViewById(R.id.numberEt)
        textCategoryView = findViewById(R.id.add_category_tv)

        textCategoryView.text = categoryReceived

        val button = findViewById<Button>(R.id.saveBt)

        editNumberView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                button.isEnabled = s?.length == 11
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editNameView.text) || TextUtils.isEmpty(editNumberView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val name = editNameView.text.toString()
                val number = editNumberView.text.toString()
                replyIntent.putExtra(EXTRA_VALUES, arrayOf(name, number, categoryReceived))
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_VALUES = "com.sallyjayz.contactlistwithroomdatabase.VALUES"
    }

}
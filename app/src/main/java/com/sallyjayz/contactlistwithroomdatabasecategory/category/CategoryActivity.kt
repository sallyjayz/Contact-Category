package com.sallyjayz.contactlistwithroomdatabasecategory.category

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sallyjayz.contactlistwithroomdatabasecategory.Application
import com.sallyjayz.contactlistwithroomdatabasecategory.R
import com.sallyjayz.contactlistwithroomdatabasecategory.adapter.CategoryAdapter
import com.sallyjayz.contactlistwithroomdatabasecategory.adapter.OnItemClickListener
import com.sallyjayz.contactlistwithroomdatabasecategory.contact.AddContactActivity
import com.sallyjayz.contactlistwithroomdatabasecategory.contact.ContactListActivity
import com.sallyjayz.contactlistwithroomdatabasecategory.model.ContactCategory

class CategoryActivity : AppCompatActivity(), OnItemClickListener {

//    private lateinit var adapter: CategoryAdapter

    private val categoryViewModel: CategoryViewModel by viewModels  {
        CategoryViewModelFactory((application as Application).categoryRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = CategoryAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        categoryViewModel.allCategory.observe(this, Observer { category ->
            category?.let { adapter.submitList(it) }
        })

    }

    override fun onItemClicked(category: ContactCategory?) {
        Toast.makeText(this,"${category?.categoryName}",Toast.LENGTH_LONG)
            .show()
        Log.i("Category","${category?.categoryName}")
        /*val categoryIntent = Intent()
        categoryIntent.putExtra(EXTRA_CATEGORY, category?.categoryName)
        setResult(Activity.RESULT_OK, categoryIntent)

        val intent = Intent(this, ContactListActivity::class.java)
        startActivity(categoryIntent)*/

        val intent = Intent(this, ContactListActivity::class.java).apply {
            putExtra(EXTRA_CATEGORY, category?.categoryName)
        }
        startActivity(intent)

        finish()
    }

    companion object {
        const val EXTRA_CATEGORY = "com.sallyjayz.contactlistwithroomdatabase.CATEGORY"
    }
}
package com.sallyjayz.contactlistwithroomdatabasecategory

import android.app.Application
import com.sallyjayz.contactlistwithroomdatabasecategory.database.CategoryRepository
import com.sallyjayz.contactlistwithroomdatabasecategory.database.ContactRepository
import com.sallyjayz.contactlistwithroomdatabasecategory.database.ContactRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class Application : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { ContactRoomDatabase.getDatabase(this, applicationScope) }
    val contactRepository by lazy { ContactRepository(database.contactDao()) }
    val categoryRepository by lazy { CategoryRepository(database.categoryDao()) }
}
package com.sallyjayz.contactlistwithroomdatabasecategory.database

import androidx.annotation.WorkerThread
import com.sallyjayz.contactlistwithroomdatabasecategory.model.ContactDetail

class ContactRepository(private val contactDao: ContactDao) {

    fun findByCategory(category:String): List<ContactDetail> {
        return contactDao.findByCategory(category)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(contact: ContactDetail) {
        contactDao.insert(contact)
    }
}

package com.sallyjayz.contactlistwithroomdatabasecategory.database

import androidx.annotation.WorkerThread
import com.sallyjayz.contactlistwithroomdatabasecategory.model.ContactCategory
import kotlinx.coroutines.flow.Flow


class CategoryRepository(private val categoryDao: CategoryDao) {

    val allCategory: Flow<List<ContactCategory>> = categoryDao.getAlphabetizedCategory()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(category: ContactCategory) {
        categoryDao.insert(category)
    }

}
package com.sallyjayz.contactlistwithroomdatabasecategory.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sallyjayz.contactlistwithroomdatabasecategory.model.ContactCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Query("SELECT * FROM category_table ORDER BY category_name ASC")
    fun getAlphabetizedCategory(): Flow<List<ContactCategory>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(contact: ContactCategory)

    @Query("DELETE FROM category_table")
    suspend fun deleteAll()

}
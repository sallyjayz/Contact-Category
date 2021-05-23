package com.sallyjayz.contactlistwithroomdatabasecategory.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sallyjayz.contactlistwithroomdatabasecategory.model.ContactCategory
import com.sallyjayz.contactlistwithroomdatabasecategory.model.ContactDetail
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {

    @Query("SELECT * FROM contact_table WHERE contact_category = :category")
    fun findByCategory(category:String): List<ContactDetail>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(contact: ContactDetail)

}
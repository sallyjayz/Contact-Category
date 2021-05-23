package com.sallyjayz.contactlistwithroomdatabasecategory.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="category_table")
data class ContactCategory(
    @PrimaryKey
    @ColumnInfo(name = "category_name")
    val categoryName: String

)

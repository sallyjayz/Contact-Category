package com.sallyjayz.contactlistwithroomdatabasecategory.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="contact_table")
data class ContactDetail (
    @PrimaryKey
    @ColumnInfo(name = "contact_name")
    val name: String,
    @ColumnInfo(name = "contact_phone")
    val number: String,
    @ColumnInfo(name = "contact_category")
    val category: String
)
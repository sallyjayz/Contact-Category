package com.sallyjayz.contactlistwithroomdatabasecategory.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sallyjayz.contactlistwithroomdatabasecategory.model.ContactCategory
import com.sallyjayz.contactlistwithroomdatabasecategory.model.ContactDetail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(ContactDetail::class, ContactCategory::class), version = 1, exportSchema = false)
abstract class ContactRoomDatabase : RoomDatabase() {

    abstract fun contactDao(): ContactDao
    abstract fun categoryDao(): CategoryDao

    private class CategoryDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var categoryDao = database.categoryDao()

                    // Delete all content here.
                    categoryDao.deleteAll()

                    // Add sample words.
                    var category = ContactCategory("Family")
                    categoryDao.insert(category)

                    category = ContactCategory("Friends")
                    categoryDao.insert(category)

                    category = ContactCategory("Colleagues")
                    categoryDao.insert(category)

                    category = ContactCategory("Tutors")
                    categoryDao.insert(category)
                }
            }
        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: ContactRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): ContactRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ContactRoomDatabase::class.java,
                    "contact_database"
                ).addCallback(CategoryDatabaseCallback(scope))
                    .allowMainThreadQueries().build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}